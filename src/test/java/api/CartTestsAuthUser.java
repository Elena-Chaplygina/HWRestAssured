package api;

import api.filter.AuthFilter;
import api.schema.AddCartRequest;
import api.schema.GetCartResponse;
import api.schema.LoginRequest;
import api.schema.ResponseMessage;
import api.service.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTestsAuthUser extends BaseTest {
    private String token;
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @BeforeAll
    public static void beforeClass() {
        LoginRequest loginBody = new LoginRequest("string", "string");
        RequestSpecification requestSpec = Service.preparePost(loginBody);
        Service.installSpecification(requestSpec);
        String TOKEN = RestAssured.given()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().body().jsonPath().getString("access_token");
        RestAssured.filters(new AuthFilter(TOKEN));
    }

    @DisplayName("Проверка получения карты клиента")
    @Test
    void verifyGettingClientCart() {
        Service.installSpecification(Service.prepareGet(), Service.responseOK200());
        GetCartResponse cart = given()
                .get("/cart")
                .then()
                .extract().as(GetCartResponse.class);
    }


    @DisplayName("Проверка добавления существующего продукта в карточку клиента")
    @Test
    void verifyAddingExistingProductToCart() {
        AddCartRequest addCartRequest = new AddCartRequest(1, 2);
        Service.installSpecification(Service.preparePost(addCartRequest), Service.responseCreated201());
        String responseBody = RestAssured.given()
                .post("/cart")
                .then()
                .extract().body().asString();
        ResponseMessage responseMessage = parseResponse(responseBody, ResponseMessage.class);
        assertEquals("Product added to cart successfully", responseMessage.getMessage());
    }


    @DisplayName("Проверка добавления в карточку клиента несуществующего продукта")
    @Test
    void verifyNotAddingNonExistingProductToCart() {
        AddCartRequest addCartRequest = new AddCartRequest(00, 2);
        Service.installSpecification(Service.preparePost(addCartRequest), Service.responseNotFound404());
        String responseBody = RestAssured.given()
                .post("/cart")
                .then()
                .extract().body().asString();
        ResponseMessage responseMessage = parseResponse(responseBody, ResponseMessage.class);
        assertEquals("Product not found", responseMessage.getMessage());

    }

    @DisplayName("Проверка удаления продукта, который был добавлен ранее")
    @Test
    void verifyRemovingPreviouslyAddedProduct() {
        Service.installSpecification(Service.responseOK200());
        ResponseMessage deleteSuccessful = given()
                .delete("/cart/1")
                .then()
                .extract().as(ResponseMessage.class);
        assertEquals("Product removed from cart", deleteSuccessful.getMessage());
    }

    @DisplayName("Проверка удаления продукта, который не был добавлен в карточку клиента")
    @Test
    void verifyRemovingNonExistingProductFromCart() {
        Service.installSpecification(Service.responseNotFound404());
        ResponseMessage deleteSuccessful = given()
                .delete("/cart/00")
                .then()
                .extract().as(ResponseMessage.class);
        assertEquals("Product not found in cart", deleteSuccessful.getMessage());
    }


    private <T> T parseResponse(String responseBody, Class<T> responseType) {
        try {
            return objectMapper.readValue(responseBody, responseType);
        } catch (JsonProcessingException e) {
            throw new AssertionFailedError(e.getMessage());
        }
    }
}



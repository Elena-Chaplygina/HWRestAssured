package api;

import api.schema.AddCartRequest;
import api.schema.ResponseMessage;
import api.schema.UnAuthMessage;
import api.service.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTestsUnAuthUser extends BaseTest{

    @DisplayName("Проверка добавления продукта в карточку клиента, если клиент не авторизован")
    @Test
    void verifyNotAddingProductToCartUnauthorizedClient() {
        AddCartRequest addCartRequest = new AddCartRequest(1, 2);
        Service.installSpecification(Service.preparePost(addCartRequest),Service.responseUnauthorized401());
        String responseBody = RestAssured.given()
                .post("/cart")
                .then()
                .extract().body().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UnAuthMessage responseMessage;
        try {
            responseMessage = objectMapper.readValue(responseBody, UnAuthMessage.class);
        } catch (JsonProcessingException e) {
            throw new AssertionFailedError(e.getMessage());
        }
        assertEquals("Missing Authorization Header", responseMessage.getMsg());


    }
}

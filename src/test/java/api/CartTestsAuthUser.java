package api;

import api.filter.AuthFilter;
import api.schema.AddCartRequest;
import api.schema.GetCartResponse;
import api.schema.ResponseMessage;
import api.service.CartService;
import api.service.UserService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTestsAuthUser extends BaseTest {

    @BeforeAll
    public static void beforeClass() {
        String TOKEN = UserService.beforeClass();
        RestAssured.filters(new AuthFilter(TOKEN));
    }

    @DisplayName("Проверка получения карты клиента")
    @Test
    void verifyGettingClientCart() {
        new CartService().getCart()
                .then().statusCode(200)
                .extract().as(GetCartResponse.class);
    }

    @DisplayName("Проверка добавления существующего продукта в карточку клиента")
    @Test
    void verifyAddingExistingProductToCart() {
        AddCartRequest addCartRequest = new AddCartRequest(1, 2);
        new CartService().addCart(addCartRequest)
                .then().statusCode(201)
                .extract().as(ResponseMessage.class);
    }

    @DisplayName("Проверка добавления в карточку клиента несуществующего продукта")
    @Test
    void verifyNotAddingNonExistingProductToCart() {
        AddCartRequest addCartRequest = new AddCartRequest(00, 2);
        ResponseMessage response = new CartService().addCart(addCartRequest)
                .then().statusCode(404)
                .extract().as(ResponseMessage.class);
        assertEquals("Product not found", response.getMessage());
    }

    @DisplayName("Проверка удаления продукта, который был добавлен ранее")
    @Test
    void verifyRemovingPreviouslyAddedProduct() {
        new CartService().deleteCart(1)
                .then().statusCode(200)
                .extract().as(ResponseMessage.class);
    }

    @DisplayName("Проверка удаления продукта, который не был добавлен в карточку клиента")
    @Test
    void verifyRemovingNonExistingProductFromCart() {
        ResponseMessage response = new CartService().deleteCart(00)
                .then().statusCode(404)
                .extract().as(ResponseMessage.class);
        assertEquals("Product not found in cart", response.getMessage());
    }

}



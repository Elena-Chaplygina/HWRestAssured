package api;

import api.schema.AddCartRequest;
import api.schema.UnAuthMessage;
import api.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTestsUnAuthUser extends BaseTest {

    @DisplayName("Проверка добавления продукта в карточку клиента, если клиент не авторизован")
    @Test
    void verifyNotAddingProductToCartUnauthorizedClient() {
        AddCartRequest addCartRequest = new AddCartRequest(1, 2);
        new CartService().addCart(addCartRequest)
                .then().statusCode(401)
                .extract().as(UnAuthMessage.class);
    }
}

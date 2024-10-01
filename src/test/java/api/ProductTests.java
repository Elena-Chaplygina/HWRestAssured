package api;

import api.schema.Product;
import api.schema.ResponseMessage;
import api.service.ProductService;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTests extends BaseTest {

    @DisplayName("Проверка получения списка продуктов")
    @Test
    void verifyGetAllProductsReturnsList() {
        new ProductService().getProductList()
                .then().statusCode(200)
                .extract().as(new TypeRef<List<Product>>() {
                });
    }

    @DisplayName("Проверка поиска продукта по id")
    @Test
    void verifyProductFoundForValidId() {
        new ProductService().getProduct(1)
                .then().statusCode(200)
                .extract().jsonPath().getList("", Product.class);
    }

    @DisplayName("Проверка поиска несуществующего продукта по id")
    @Test
    void verifyProductNotFoundForInvalidId() {
        ResponseMessage loginError = new ProductService().getProduct(00)
                .then().statusCode(404)
                .extract().as(ResponseMessage.class);
        assertEquals("Product not found", loginError.getMessage());
    }
}

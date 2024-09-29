package api;

import api.schema.Product;
import api.schema.ResponseMessage;
import api.service.Service;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTests extends BaseTest {

    private static final String PRODUCTS_ENDPOINT = "/products";

    @DisplayName("Проверка получения списка продуктов")
    @Test
    void verifyGetAllProductsReturnsList() {
        Service.installSpecification(Service.prepareGet(), Service.responseOK200());
        List<Product> products = given()
                .get(PRODUCTS_ENDPOINT)
                .then()
                .extract().as(new TypeRef<List<Product>>() {
                });
        assertThat(products, is(not(empty())));
    }

    @DisplayName("Проверка поиска продукта по id")
    @Test
    void verifyProductFoundForValidId() {
        Service.installSpecification(Service.prepareGet(), Service.responseOK200());
        List<Product> products = fetchProductsById(1);
        assertThat(products, hasSize(1));
        assertEquals(1, products.get(0).getId());
        assertNotNull(products.get(0).getPrice());
        assertNotNull(products.get(0).getName());
        assertNotNull(products.get(0).getCategory());
        assertNotNull(products.get(0).getDiscount());
    }

    @DisplayName("Проверка поиска несуществующего продукта по id")
    @Test
    void verifyProductNotFoundForInvalidId() {
        Service.installSpecification(Service.prepareGet(), Service.responseNotFound404());
        ResponseMessage loginError = given()
                .get("/products/00")
                .then()
                .extract().as(ResponseMessage.class);

        assertEquals("Product not found", loginError.getMessage());
    }

    private List<Product> fetchProductsById(int id) {
        Response response = given().get(PRODUCTS_ENDPOINT + "/" + id).then().extract().response();
        return response.jsonPath().getList("", Product.class);
    }
}

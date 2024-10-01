package api.service;

import api.schema.AddCartRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CartService {
    private final static String PATH = "cart";

    public Response getCart() {
        return Service.prepareGet().get(PATH);
    }

    public Response addCart(AddCartRequest body) {
        return Service.preparePost(body).post(PATH);
    }

    public Response deleteCart(Integer id) {
        return RestAssured.given().delete(PATH + "/" + id);
    }
}


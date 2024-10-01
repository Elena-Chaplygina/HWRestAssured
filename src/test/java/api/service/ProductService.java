package api.service;

import io.restassured.response.Response;

public class ProductService {
    private final static String PATH = "products";

    public Response getProductList() {
        return Service.prepareGet().get(PATH);
    }

    public Response getProduct(Integer id) {
        return Service.prepareGet().get(PATH + "/" + id);
    }
}

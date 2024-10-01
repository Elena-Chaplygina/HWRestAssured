package api.service;

import api.schema.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserService {

    private final static String PATH = "login";
    private final static String LOGIN = "string";
    private final static String PASSWORD = "string";


    public Response postUser(LoginRequest body) {
        return Service.preparePost(body).post(PATH);
    }

    public static String beforeClass() {
        LoginRequest loginBody = new LoginRequest(LOGIN, PASSWORD);
        String token = RestAssured.given().contentType(ContentType.JSON)
                .body(loginBody).post(PATH)
                .then().extract().body().jsonPath().getString("access_token");
        return token;
    }
}

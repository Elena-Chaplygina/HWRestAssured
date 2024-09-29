package api;

import api.schema.LoginRequest;
import api.schema.ResponseMessage;
import api.service.Service;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests extends BaseTest {

    @DisplayName("Проверка авторизации пользователя, зарегистрированного в системе")
    @Test
    void verifyUserAuthorization() {
        LoginRequest loginBody = new LoginRequest("string", "string");
        RequestSpecification requestSpec = Service.preparePost(loginBody);
        Service.installSpecification(requestSpec, Service.responseOK200());
        RestAssured.given()
                .post(LOGIN_ENDPOINT)
                .then()
                .body("$", hasKey("access_token"));
    }

    @DisplayName("Проверка авторизации незарегистрированного пользователя")
    @Test
    void verifyLoginUnregisteredUser() {
        Service.installSpecification(Service.responseUnauthorized401());
        LoginRequest loginBody = new LoginRequest("decimal", "string");
        ResponseMessage loginError = loginAndGetResponse(loginBody);
        assertEquals("Invalid credentials", loginError.getMessage());
    }

    @DisplayName("Проверка авторизации зарегистрированного пользователя с неверным паролем")
    @Test
    void verifyUserLoginFailureWithWrongPassword() {
        Service.installSpecification(Service.responseUnauthorized401());
        LoginRequest loginBody = new LoginRequest("string", "decimal");
        ResponseMessage loginError = loginAndGetResponse(loginBody);
        assertEquals("Invalid credentials", loginError.getMessage());
    }

    private ResponseMessage loginAndGetResponse(LoginRequest login) {
        Service.preparePost(login);
        RequestSpecification requestSpec = Service.preparePost(login);
        Service.installSpecification(requestSpec);
        return RestAssured.given()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().as(ResponseMessage.class);
    }
}

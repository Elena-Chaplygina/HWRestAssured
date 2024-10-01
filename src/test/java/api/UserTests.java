package api;

import api.schema.LoginRequest;
import api.schema.ResponseMessage;
import api.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests extends BaseTest {

    @DisplayName("Проверка авторизации пользователя, зарегистрированного в системе")
    @Test
    void verifyUserAuthorization() {
        LoginRequest loginBody = new LoginRequest("string", "string");
        new UserService().postUser(loginBody)
                .then().statusCode(200)
                .body("$", hasKey("access_token"));
    }

    @DisplayName("Проверка авторизации незарегистрированного пользователя")
    @Test
    void verifyLoginUnregisteredUser() {
        LoginRequest loginBody = new LoginRequest("decimal", "string");
        ResponseMessage loginError = new UserService().postUser(loginBody)
                .then().statusCode(401)
                .extract().as(ResponseMessage.class);
        assertEquals("Invalid credentials", loginError.getMessage());
    }

    @DisplayName("Проверка авторизации зарегистрированного пользователя с неверным паролем")
    @Test
    void verifyUserLoginFailureWithWrongPassword() {
        LoginRequest loginBody = new LoginRequest("string", "decimal");
        ResponseMessage loginError = new UserService().postUser(loginBody)
                .then().statusCode(401)
                .extract().as(ResponseMessage.class);
        assertEquals("Invalid credentials", loginError.getMessage());
    }
}

package api.schema;

import lombok.Value;

@Value
public class LoginRequest implements Schema{
    String username;
    String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

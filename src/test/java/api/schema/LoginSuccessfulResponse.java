package api.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginSuccessfulResponse {

    @JsonProperty("access_token")
    double AccessToken;

    public double getAccessToken() {
        return AccessToken;
    }

    public LoginSuccessfulResponse() {
    }
}

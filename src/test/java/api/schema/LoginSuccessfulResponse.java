package api.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccessfulResponse {

    @JsonProperty("access_token")
    private double AccessToken;

    public double getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(double accessToken) {
        AccessToken = accessToken;
    }
}

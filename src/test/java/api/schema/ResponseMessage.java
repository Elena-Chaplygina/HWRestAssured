package api.schema;

import lombok.Data;

@Data
public class ResponseMessage {

    private String message;

    public String getMessage() {
        return message;
    }
}

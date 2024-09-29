package api.schema;

import lombok.Data;

@Data
public class UnAuthMessage {
    private String msg;

    public String getMsg() {
        return msg;
    }
}

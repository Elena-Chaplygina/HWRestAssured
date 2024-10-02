package api.schema;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UnAuthMessage {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

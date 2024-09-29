package api.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class GetCartResponse {

    List<Cart> cart;

    @JsonProperty("total_price")
    double totalPrice;

    @JsonProperty("total_discount")
    double totalDuscount;


}

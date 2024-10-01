package api.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class Cart {

    int id;
    String name;
    @JsonProperty("category")
    String category;
    double price;
    double discount;
    long quantity;
}

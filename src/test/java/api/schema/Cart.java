package api.schema;

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
    String category;
    double price;
    double discount;
    long quantity;
}

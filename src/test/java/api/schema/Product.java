package api.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data

public class Product {

    String category;
    Double discount;
    Integer id;
    String name;
    Double price;

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Double getDiscount() {
        return discount;
    }

    public String getCategory() {
        return category;
    }
}

package api.schema;

import lombok.*;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class GetProductResponse {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

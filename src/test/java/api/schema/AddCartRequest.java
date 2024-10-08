package api.schema;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AddCartRequest implements Schema {

    @JsonProperty("product_id")
    private int productId;

    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public AddCartRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

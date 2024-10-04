package api.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class GetCartResponse {

    private List<Cart> cart;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("total_discount")
    private double totalDuscount;

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public double getTotalDuscount() {
        return totalDuscount;
    }

    public void setTotalDuscount(double totalDuscount) {
        this.totalDuscount = totalDuscount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

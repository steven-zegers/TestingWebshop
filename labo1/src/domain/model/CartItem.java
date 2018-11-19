package domain.model;

public class CartItem {
    private Product product;
    private int quantity;
    public CartItem() {

    }
    public CartItem(Product p, int quantity) {
        setProduct(p);
        setQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return quantity*product.getPrice();
    }
}

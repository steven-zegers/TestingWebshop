package domain.model;

public class CartItem {
    private Book book;
    private int quantity;
    public CartItem() {

    }
    public CartItem(Book book, int quantity) {
        setProduct(book);
        setQuantity(quantity);
    }

    public Book getProduct() {
        return book;
    }

    public void setProduct(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity should be greater than zero");
        this.quantity = quantity;
    }

    public double getPrice() {
        return quantity*book.getPrice();
    }
}

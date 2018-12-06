package domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    public Map<String, CartItem> productMap;

    public ShoppingCart() {
        productMap = new HashMap<>();
    }

    public void add(CartItem product) {
        if(productMap.containsKey(product.getProduct().getTitle())){
            CartItem item = productMap.get(product.getProduct().getTitle());
            System.out.println(item.getQuantity());
            System.out.println(product.getQuantity());
            item.setQuantity(item.getQuantity() + product.getQuantity());
        } else {
            productMap.put(product.getProduct().getTitle(), product);
        }

    }

    public Collection<CartItem> getItems() {
        return productMap.values();
    }

    public void delete(String title) {
        System.out.println("Trying to delete item: " + title);
        if (!productMap.containsKey(title)) {
            throw new DomainException("Dit item zit niet in uw shopping cart");
        }
        for(CartItem product: getItems()) {
            System.out.println(product.getProduct().getTitle());
        }
        productMap.remove(title);
        for(CartItem product : getItems()) {
            System.out.println(product.getProduct().getTitle());
        }
    }

    public void delete(Book book) {
        productMap.remove(book);
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : getItems()) {
            total += item.getPrice();
        }
        return total;
    }

    public void pay() {
        productMap.clear();
    }
}

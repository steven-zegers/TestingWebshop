package domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    public Map<Integer, CartItem> productMap;
    public ShoppingCart() {
        productMap = new HashMap<>();
    }

    public void add(CartItem product) {
        if(productMap.containsKey(product.getProduct().getProductId())){
            CartItem item = productMap.get(product.getProduct().getProductId());
            System.out.println(item.getQuantity());
            System.out.println(product.getQuantity());
            item.setQuantity(item.getQuantity() + product.getQuantity());
        } else {
            productMap.put(product.getProduct().getProductId(), product);
        }

    }

    public Collection<CartItem> getItems() {
        return productMap.values();
    }

    public void delete(int id) {
        System.out.println("Trying to delete item: " + id);
        if (!productMap.containsKey(id)) {
            throw new DomainException("Dit item zit niet in uw shopping cart");
        }
        for(CartItem product: getItems()) {
            System.out.println(product.getProduct().getProductId());
        }
        productMap.remove(id);
        for(CartItem product : getItems()) {
            System.out.println(product.getProduct().getProductId());
        }
    }

    public void delete(Product product) {
        productMap.remove(product);
    }
}

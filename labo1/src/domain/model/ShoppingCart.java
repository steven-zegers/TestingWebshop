package domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    Map<Integer, Product> productMap;
    public ShoppingCart() {
        productMap = new HashMap<>();
    }

    public void add(Product product) {
        System.out.println(product.getProductId());
        productMap.put(product.getProductId(), product);
    }

    public Collection<Product> getItems() {
        return productMap.values();
    }

    public void delete(int id) {
        productMap.remove(id);
    }

    public void delete(Product product) {
        productMap.remove(product);
    }
}

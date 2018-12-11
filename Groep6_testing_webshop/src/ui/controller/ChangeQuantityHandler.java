package ui.controller;

import domain.model.CartItem;
import domain.model.ShopService;
import domain.model.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

public class ChangeQuantityHandler extends RequestHandler {
    public ChangeQuantityHandler() {

    }
    public ChangeQuantityHandler(ShopService service) {
        super(service);
    }
    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<String> notifications = new ArrayList<>();
        String title = request.getParameter("id");
        HttpSession session = request.getSession();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch(NumberFormatException e) {
            errors.add("Please input a valid quantity!");
            ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
            Collection<CartItem> cartItems = cart.getItems();
            request.setAttribute("shopCart", cartItems);
            request.setAttribute("errors", errors);
            return "showCart.jsp";
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
        Collection<CartItem> cartItems = cart.getItems();
        for (CartItem item : cartItems) {
            if (item.getProduct().getTitle().equals(title)) {
                try {
                    item.setQuantity(quantity);
                    notifications.add("Updated the quantity of book: " + item.getProduct().getTitle());
                } catch(IllegalArgumentException e) {
                    errors.add(e.getMessage());
                    request.setAttribute("errors", errors);
                }

            }
        }
        request.setAttribute("shopCart", cartItems);
        request.setAttribute("notifications", notifications);
        request.setAttribute("total", cart.getTotalPrice());
        return "showCart.jsp";
    }
}

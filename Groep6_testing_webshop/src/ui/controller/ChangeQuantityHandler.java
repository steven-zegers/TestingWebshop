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
        String title = request.getParameter("id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
        Collection<CartItem> cartItems = cart.getItems();
        for (CartItem item : cartItems) {
            if (item.getProduct().getTitle().equals(title)) {
                try {
                    item.setQuantity(quantity);
                } catch(IllegalArgumentException e) {
                    errors.add(e.getMessage());
                    request.setAttribute("errors", errors);
                }

            }
        }
        request.setAttribute("shopCart", cartItems);

        request.setAttribute("total", cart.getTotalPrice());
        return "showCart.jsp";
    }
}

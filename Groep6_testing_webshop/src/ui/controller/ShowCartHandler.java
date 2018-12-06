package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class ShowCartHandler extends RequestHandler {
    public ShowCartHandler() {

    }

    public ShowCartHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        double total = 0;
        HttpSession session = request.getSession();
        if (session.getAttribute("person") != null) {
            Person p = (Person) session.getAttribute("person");
            if (p.getShoppingCart() == null) {
                ShoppingCart shoppingCart = new ShoppingCart();
                p.setShoppingCart(shoppingCart);
            }
            session.setAttribute("shopCart", p.getShoppingCart());
        }
        if (session.getAttribute("shopCart") != null) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
            Collection<CartItem> producten = cart.getItems();
            request.setAttribute("shopCart", producten);

            total = cart.getTotalPrice();
        }
        session.setAttribute("total", total);
        return "showCart.jsp";
    }
}

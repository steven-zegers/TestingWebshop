package ui.controller;

import domain.model.Product;
import domain.model.ShopService;
import domain.model.ShoppingCart;

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
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
        Collection<Product> producten = cart.getItems();
        request.setAttribute("shopCart", producten);
        return "showCart.jsp";
    }
}

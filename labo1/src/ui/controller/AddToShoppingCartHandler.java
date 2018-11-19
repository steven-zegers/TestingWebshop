package ui.controller;

import domain.model.CartItem;
import domain.model.Product;
import domain.model.ShopService;
import domain.model.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class AddToShoppingCartHandler extends RequestHandler {
    public AddToShoppingCartHandler() {

    }
    public AddToShoppingCartHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        Product p;
        CartItem cartItem = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            p = getShopService().getProduct(id);
            cartItem = new CartItem(p, Integer.parseInt(request.getParameter("quantity")));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ID should be an integer");
        }
        if (session.getAttribute("shopCart") == null) {
            ShoppingCart cart = new ShoppingCart();
            session.setAttribute("shopCart", cart);
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
        cart.add(cartItem);
        request.setAttribute("producten", getShopService().getProducts());
        return "productOverview.jsp";
    }
}

package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

public class AddToShoppingCartHandler extends RequestHandler {
    public AddToShoppingCartHandler() {

    }
    public AddToShoppingCartHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        Book book;
        CartItem cartItem = null;
        try {
            String title = request.getParameter("id");
            System.out.println(title);
            book = getShopService().getProduct(title);
            try {
                cartItem = new CartItem(book, Integer.parseInt(request.getParameter("quantity")));
            } catch(IllegalArgumentException e) {
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                request.setAttribute("producten", getShopService().getProducts());
                return "productOverview.jsp";
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("ID should be an integer");
        }

        if(session.getAttribute("person") != null) {
            Person person = (Person) session.getAttribute("person");
            if(person.getShoppingCart() == null) {
                ShoppingCart personShoppingCart = new ShoppingCart();
                person.setShoppingCart(personShoppingCart);
            }
            session.setAttribute("shopCart", person.getShoppingCart());
        } else {
            if (session.getAttribute("shopCart") == null) {
                ShoppingCart cart = new ShoppingCart();
                session.setAttribute("shopCart", cart);
            }
            session.setAttribute("shopCart", session.getAttribute("shopCart"));
        }
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
        cart.add(cartItem);
        ArrayList<String> message = new ArrayList<>();
        message.add("Product added to shoppingcart!");
        request.setAttribute("errors", message);
        request.setAttribute("producten", getShopService().getProducts());
        return "productOverview.jsp";
    }
}

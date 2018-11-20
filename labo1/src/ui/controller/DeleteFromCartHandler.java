package ui.controller;

import domain.model.DomainException;
import domain.model.Product;
import domain.model.ShopService;
import domain.model.ShoppingCart;
import org.eclipse.jetty.http.HttpParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeleteFromCartHandler extends RequestHandler {


    public DeleteFromCartHandler() {

    }
    public DeleteFromCartHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        if (session.getAttribute("shopCart") != null) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
            try {
                System.out.println("Deleting item " + id);
                cart.delete(id);
            } catch (DomainException e) {
                errors.add(e.getMessage());
            }
            session.setAttribute("shopCart", cart);
            request.setAttribute("shopCart", cart.getItems());
            request.setAttribute("total", cart.getTotalPrice());
        } else {
            errors.add("U heeft geen shopping cart dus kan ook niets verwijderen");
        }
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        return "showCart.jsp";
    }
}

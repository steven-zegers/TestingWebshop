package ui.controller;

import domain.model.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClearCartHandler extends RequestHandler {
    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shopCart");
        cart.pay();
        System.out.println("shopping cart empty");
        request.setAttribute("shopCart", cart.getItems());
        return "showCart.jsp";
    }
}

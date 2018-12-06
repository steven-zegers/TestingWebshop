package ui.controller;

import domain.model.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderHandler extends RequestHandler {
    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shopCart");
        shoppingCart.pay();
        return "PaymentConfirmed.jsp";
    }
}

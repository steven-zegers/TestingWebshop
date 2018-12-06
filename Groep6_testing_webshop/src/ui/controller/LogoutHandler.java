package ui.controller;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler extends RequestHandler {
    public LogoutHandler() {

    }
    public LogoutHandler(ShopService service) {
        super(service);
    }
    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("login", "no");
        session.setAttribute("person", null);
        session.setAttribute("shopCart", null);
        return "index.jsp";
    }
}

package ui.controller;

import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class LoginHandler extends RequestHandler {
    public LoginHandler() {

    }

    public LoginHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        HttpSession session = request.getSession();
        String id = request.getParameter("userid");
        String password = request.getParameter("password");
        if (id.isEmpty() || password.isEmpty()) {
            errors.add("Please fill in both fields.");
        }
        if (errors.size() == 0) {
            Person p = getShopService().getUserIfAuthenticated(id, password);
            if (p == null) {
                errors.add("Invalid userid or password.");
                request.setAttribute("errors", errors);
                return "index.jsp";
            } else {
                session.setAttribute("person", p);
                session.setAttribute("login", "yes");
                return "index.jsp";
            }
        } else {
            request.setAttribute("errors", errors);
            return "index.jsp";
        }

    }
}

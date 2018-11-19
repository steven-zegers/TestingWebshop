package ui.controller;

import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPassHandler extends RequestHandler {
    public CheckPassHandler() {

    }
    public CheckPassHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        Person p;
        p = getShopService().getPerson(request.getParameter("userid"));

        String s;
        if (p.isCorrectPassword(request.getParameter("password"))) {
            s = "Password is ok";
        } else {
            s = "Password is niet ok";
        }
        request.setAttribute("tekst", s);

        request.setAttribute("person", p);
        return "checkPass.jsp";
    }
}

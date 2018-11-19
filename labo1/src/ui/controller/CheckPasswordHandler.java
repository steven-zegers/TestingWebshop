package ui.controller;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPasswordHandler extends RequestHandler {
    public CheckPasswordHandler() {

    }
    public CheckPasswordHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("person", getShopService().getPerson(request.getParameter("id")));
        return "checkPass.jsp";
    }
}

package ui.controller;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpHandler extends RequestHandler{
    public SignUpHandler() {

    }
    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        return "signUp.jsp";
    }

    public SignUpHandler(ShopService service) {
        super(service);
    }


}

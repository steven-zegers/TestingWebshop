package ui.controller;

import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeHandler extends RequestHandler {
    public HomeHandler() {

    }
    public HomeHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals("prefered")) {
                    if (cookie.getValue().equals("yes")) {
                        request.setAttribute("quote", "yes");
                    } else {
                        request.setAttribute("quote", "no");
                    }
                }
            }
        }
        return "index.jsp";
    }
}

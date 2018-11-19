package ui.controller;

import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuotePreferenceHandler extends RequestHandler {
    public QuotePreferenceHandler() {

    }
    public QuotePreferenceHandler(ShopService service) {
        super.setShopService(service);
    }
    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("Quote").equals("yes")) {
            String preference = request.getParameter("Quote");
            Cookie cookie = new Cookie("prefered", preference);
            response.addCookie(cookie);
            request.setAttribute("quote", "yes");
        }
        if (request.getParameter("Quote").equals("no")) {
            String preference = request.getParameter("Quote");
            request.setAttribute("quote", "no");
            Cookie cookie = new Cookie("prefered", preference);
            response.addCookie(cookie);
        }
        return "index.jsp";
    }
}

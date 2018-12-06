package ui.controller;

import domain.model.Book;
import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class SortHandler extends RequestHandler {
    public SortHandler(){

    }
    public SortHandler(ShopService service) {
        super.setShopService(service);
    }
    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        String sortOn = request.getParameter("sort");
        List<Book> books = getShopService().getProducts();
        sortList(sortOn, books);
        Cookie cookie = new Cookie("sort", sortOn);
        response.addCookie(cookie);
        request.setAttribute("sort", sortOn);
        request.setAttribute("producten", books);
        return "productOverview.jsp";
    }
}

package ui.controller;

import domain.model.Book;
import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductOverviewHandler extends RequestHandler{
    public ProductOverviewHandler() {

    }
    public ProductOverviewHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        List<Book> producten = getShopService().getProducts();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals("sort")) {
                    String sortMethod = cookie.getValue();
                    request.setAttribute("sort", sortMethod);
                    sortList(sortMethod, producten);
                }
            }
        }
        request.setAttribute("producten", producten);
        return "productOverview.jsp";
    }
}

package ui.controller;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchBookHandler extends RequestHandler {
    public SearchBookHandler() {

    }

    public SearchBookHandler(ShopService shopService) {
        super(shopService);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        return "searchBook.jsp";
    }
}

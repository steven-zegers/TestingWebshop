package ui.controller;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductOverviewHandler extends RequestHandler{
    public ProductOverviewHandler() {

    }
    public ProductOverviewHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("producten", getShopService().getProducts());
        return "productOverview.jsp";
    }
}

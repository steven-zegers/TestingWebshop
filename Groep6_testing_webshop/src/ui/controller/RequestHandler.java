package ui.controller;

import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public abstract class RequestHandler {
    private ShopService shopService;
    abstract String handle(HttpServletRequest request, HttpServletResponse response);
    public RequestHandler() {

    }
    public RequestHandler(ShopService service) {
        setShopService(service);
    }

    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        if (shopService == null) {
            throw new IllegalArgumentException("Shop service does not exist");
        }
        this.shopService = shopService;
    }

    public void sortList(String param, List<Person> persons) {
        switch(param) {
            case "fname":
                Collections.sort(persons, new PersonFirstNameComparator());
                break;
            case "lname":
                Collections.sort(persons, new PersonLastNameComparator());
                break;
        }
    }
}

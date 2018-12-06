package ui.controller;

import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;


public class PersonOverviewHandler extends RequestHandler {
    public PersonOverviewHandler() {

    }
    public PersonOverviewHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        List<Person> personList = super.getShopService().getPersons();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals("sort")) {
                    String sortMethod = cookie.getValue();
                    request.setAttribute("sort", sortMethod);
                    sortList(sortMethod, personList);
                }
            }
        }
        request.setAttribute("persons", personList);
        return "personoverview.jsp";
    }
}

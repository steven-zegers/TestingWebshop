package ui.controller;

import domain.db.DbException;
import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class SubmitHandler extends RequestHandler {
    public SubmitHandler() {

    }

    public SubmitHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        Person p = new Person();
        ArrayList<String> errors = new ArrayList<>();
        String userId = request.getParameter("userId");
        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String password = request.getParameter("password");
        String streetName = request.getParameter("streetName");
        int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
        String city = request.getParameter("city");

        try {
            p.setUserid(userId);
            request.setAttribute("userId", userId);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            p.setFirstName(firstname);
            request.setAttribute("firstName", firstname);
        } catch (IllegalArgumentException e){
            errors.add(e.getMessage());
        }

        try {
            p.setLastName(lastname);
            request.setAttribute("lastName", lastname);
        } catch (IllegalArgumentException e){
            errors.add(e.getMessage());
        }

        try {
            p.setPassword(password);
            request.setAttribute("password", password);
        } catch(IllegalArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            p.setStreetName(streetName);
            request.setAttribute("streetName", streetName);
        } catch(IllegalArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            p.setStreetNumber(streetNumber);
            request.setAttribute("streetNumber", streetNumber);
        } catch(IllegalArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            p.setCity(city);
            request.setAttribute("city", city);
        } catch(IllegalArgumentException e) {
            errors.add(e.getMessage());
        }

        if (errors.size() == 0) {
            try {
                getShopService().getPersonDb().add(p);
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }

        if (errors.size() == 0) {
            request.setAttribute("persons", getShopService().getPersons());
            return "personoverview.jsp";
        } else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }
}

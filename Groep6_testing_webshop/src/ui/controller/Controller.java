package ui.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.RequestDispatcher;

import domain.db.*;
import domain.model.Person;
import domain.model.ShopService;
import sun.misc.Request;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private PersonDb persons;
    private ProductDb productdb;
    private HandlerFactory handlerFactory = new HandlerFactory();
    private ShopService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        Properties properties = new Properties();
        properties.setProperty("user", context.getInitParameter("user"));
        properties.setProperty("password", context.getInitParameter("password"));
        properties.setProperty("ssl", context.getInitParameter("ssl"));
        properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
        properties.setProperty("sslmode", context.getInitParameter("sslmode"));
        properties.setProperty("url", context.getInitParameter("url"));
        service =  new ShopService(properties);
        this.persons = service.getPersonDb();
        this.productdb = service.getProductDb();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            handleRequest(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            handleRequest(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String action = "Home";

        if(request.getParameter("action") != null){
            action = request.getParameter("action");

        }
        try {
            RequestHandler handler = handlerFactory.getHandler(action, service);
            String destination = handler.handle(request, response);
            System.out.println(destination);
            RequestDispatcher view = request.getRequestDispatcher(destination);
            view.forward(request, response);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new ServletException(e.getMessage());
        }


        /*String destination = "";
        switch(action) {
            case "home":
                destination = "index.jsp";
                break;
            case "overview":
                destination = "personoverview.jsp";
                request.setAttribute("persons", persons.getAll());
                break;
            case "signUp":
                destination = "signUp.jsp";
                break;
            case "submit":
                destination = handleSubmit(request, response);
                break;
            case "productOverview":
                destination = "productOverview.jsp";
                request.setAttribute("producten", productdb.getAll());
                break;
            case "checkPassword":
                destination = "checkPass.jsp";
                request.setAttribute("person", service.getPerson(request.getParameter("id")));
                break;
            case "checkPass":
                destination = checkPassword(request, response);
                break;
        }
        RequestDispatcher view=request.getRequestDispatcher(destination);
        view.forward(request, response);*/
    }

    /*protected String checkPassword(HttpServletRequest request, HttpServletResponse response) {
        Person p;
        p = service.getPerson(request.getParameter("userid"));

        String s;
            if (p.isCorrectPassword(request.getParameter("password"))) {
               s = "Password is ok";
            } else {
                s = "Password is niet ok";
            }
            request.setAttribute("tekst", s);

        request.setAttribute("person", p);
        return "checkPass.jsp";
    }

    protected String handleSubmit(HttpServletRequest request, HttpServletResponse response) {
        Person p = new Person();

        ArrayList<String> errors = new ArrayList<>();
        String userid = request.getParameter("userid");
        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            p.setUserid(userid);
            request.setAttribute("userid", userid);
        } catch (IllegalArgumentException e){
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
            p.setEmail(mail);
            request.setAttribute("email", mail);

        } catch(IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        if (errors.size() == 0) {
            try {
                persons.add(p);
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }

        if (errors.size() == 0) {
            request.setAttribute("persons", persons.getAll());
            return "personoverview.jsp";
        } else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }*/
}

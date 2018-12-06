package ui.controller;

import domain.model.ShopService;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;

public class HandlerFactory {
    public RequestHandler getHandler(String handlerName, ShopService service) throws ServletException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        RequestHandler handler = null;

        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName + "Handler");
            System.out.println("ui.controller." + handlerName + "Handler");
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setShopService(service);
        } catch (ClassNotFoundException e) {

            throw new ServletException(e.getMessage());
        }
        return handler;
    }
}

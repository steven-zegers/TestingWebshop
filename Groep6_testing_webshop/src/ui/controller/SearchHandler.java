package ui.controller;

import domain.model.Book;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SearchHandler extends RequestHandler {
    public SearchHandler() {

    }

    public SearchHandler(ShopService service) {
        super(service);
    }

    @Override
    String handle(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        String searchString = request.getParameter("searchString");

        if (searchString.length() < 3) {
            errors.add("Keyword should be atleast 3 characters long!");
            request.setAttribute("errors", errors);
            return "searchBook.jsp";
        }
        String method = request.getParameter("method");
        if (method.equals("title")) {
            List<Book> producten = getShopService().getProducts();
            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : producten) {
                if(book.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
                    matchingBooks.add(book);
                }
            }
            request.setAttribute("producten", matchingBooks);
        } else if (method.equals("author")) {
            List<Book> producten = getShopService().getProducts();
            List<Book> matchingBooks = new ArrayList<>();
            for (Book book : producten) {
                if(book.getAuthor().toLowerCase().contains(searchString.toLowerCase())) {
                    matchingBooks.add(book);
                }
            }
            request.setAttribute("producten", matchingBooks);
        } else {
            errors.add("Please select a valid search method.");
            request.setAttribute("errors", errors);
            return "searchBook.jsp";
        }
        return "searchBook.jsp";
    }
}

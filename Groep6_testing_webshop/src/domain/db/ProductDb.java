package domain.db;

import domain.model.Book;
import domain.model.Product;

import java.util.List;

public interface ProductDb {
    Book get(String title);

    List<Book> getAll();

    void add(Book book);

    void update(Book book);

    void delete(String title);

    int getNumbeOfProducts();
}

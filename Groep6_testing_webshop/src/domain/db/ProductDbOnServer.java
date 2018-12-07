package domain.db;

import domain.model.Book;
import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDbOnServer implements ProductDb {
    private Properties properties;
    private String url;
    private PreparedStatement statement;
    public ProductDbOnServer (Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Book get(String title){
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("SELECT * FROM book WHERE title = ?");
            statement.setString(1, title);
            ResultSet result = statement.executeQuery();
            result.next();
            String bookTitle = result.getString("title");
            String author = result.getString("author");
            Double price = result.getDouble("price");
            return new Book(bookTitle, author, price);
        } catch (SQLException e) {
            throw new DbException("Couldn't find this product " + e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public List<Book> getAll(){

        ArrayList<Book> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("SELECT * FROM book");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String bookTitle = result.getString("title");
                String author = result.getString("author");
                Double price = result.getDouble("price");
                Book book = new Book(bookTitle, author, price);
                products.add(book);
            }
            return products;
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch(SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public void add(Book book){
        if(book == null){
            throw new DbException("No product given");
        }
        String title = book.getTitle();
        String author = book.getAuthor();
        double price = book.getPrice();

        try (Connection connection = DriverManager.getConnection(url, properties)) {
            System.out.println(title);
            statement = connection.prepareStatement("INSERT INTO book (title,author,price) VALUES (?,?,?)");
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setDouble(3, price);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public void update(Book book){
        String title = book.getTitle();
        delete(title);
        add(book);
    }

    @Override
    public void delete(String title){
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("DELETE from book where title = ?");
            statement.setString(1, title);
            statement.execute();
        } catch (SQLException e) {
            throw new DbException("This product isnt in the database");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public int getNumbeOfProducts() {
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("COUNT(*) as totaal FROM book");
            ResultSet result = statement.executeQuery();
            return result.getInt("totaal");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}

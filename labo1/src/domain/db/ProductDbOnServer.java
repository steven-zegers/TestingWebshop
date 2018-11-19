package domain.db;

import domain.model.Person;
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
    public Product get(int id){
        if (id < 0) {
            throw new DbException("Incorrect product id");
        }
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("SELECT * FROM product WHERE productid = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            int productID = result.getInt("productid");
            String name = result.getString("name");
            String description = result.getString("description");
            Double price = result.getDouble("price");
            Product p = new Product(productID, name, description, price);
            return p;
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
    public List<Product> getAll(){

        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("SELECT * FROM product");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("productid");
                String name = result.getString("name");
                String description = result.getString("description");
                Double price = result.getDouble("price");
                Product p = new Product(productID, name, description, price);
                products.add(p);
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
    public void add(Product product){
        if(product == null){
            throw new DbException("No product given");
        }
        int productId = product.getProductId();
        String naam = product.getName();
        String description = product.getDescription();
        double price = product.getPrice();

        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("INSERT INTO product (productid,naam,description,price) VALUES (?,?,?,?,?)");
            statement.setInt(1, productId);
            statement.setString(2, naam);
            statement.setString(3, description);
            statement.setDouble(4, price);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Product already exists");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public void update(Product product){
        int productid = product.getProductId();
        delete(productid);
        add(product);
    }

    @Override
    public void delete(int id){
        if(id < 0){
            throw new DbException("No valid id given");
        }
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("DELETE from person where userid = ?");
            statement.setInt(1, id);
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
            statement = connection.prepareStatement("COUNT(*) as totaal FROM product");
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

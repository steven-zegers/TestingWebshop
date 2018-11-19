package domain.db;

import domain.model.Order;

import java.sql.PreparedStatement;
import java.util.Properties;

public class OrderDbOnServer {
    private Properties properties;
    private String url;
    private PreparedStatement statement;

    public OrderDbOnServer (Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void add(Order order) {

    }
}

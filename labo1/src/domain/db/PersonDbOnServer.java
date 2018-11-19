package domain.db;

import domain.model.Person;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.PreparedStatement;

public class PersonDbOnServer implements PersonDb {
    private Properties properties;
    private String url;
    private PreparedStatement statement;
    public PersonDbOnServer(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Person get(String personId) {
        System.out.println(personId);
        if (personId == null ) {
            throw new DbException("No user id given");
        }
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("SELECT * FROM person WHERE userid = ?");
            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();
            result.next();
            String voornaam = result.getString("firstname");
            String achternaam = result.getString("lastname");
            String userid = result.getString("userid");
            String email = result.getString("email");
            String password = result.getString("password");
            Person p = new Person(userid, email, password, voornaam, achternaam);
            return p;
        } catch (SQLException e) {
            throw new DbException("Couldn't find this person " + e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public void changePassword() {

    }
    @Override
    public List<Person> getAll() {
        ArrayList<Person> listPersons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("SELECT * FROM person");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String voornaam = result.getString("firstname");
                String achternaam = result.getString("lastname");
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                Person p = new Person(userid, email, password, voornaam, achternaam);
                listPersons.add(p);
            }
            return listPersons;
        } catch (SQLException e) {
            throw new DbException("Couldn't find this person " + e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }
        String userid = person.getUserid();
        String password = person.getPassword();
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String email = person.getEmail();
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("INSERT INTO person (firstname,lastname,userid,email,password) VALUES (?,?,?,?,?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, userid);
            statement.setString(4, email);
            statement.setString(5, password);
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
    public void update(Person person) {
        String userid = person.getUserid();
        delete(userid);
        add(person);
    }

    @Override
    public void delete(String personId) {
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("DELETE from person where userid = ?");
            statement.setString(1, personId);
            statement.execute();
        } catch (SQLException e) {
            throw new DbException("This person isnt in the database");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public int getNumberOfPersons() {
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            statement = connection.prepareStatement("COUNT(*) as totaal FROM person");
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

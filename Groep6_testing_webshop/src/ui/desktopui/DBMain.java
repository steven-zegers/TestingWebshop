package ui.desktopui;

import java.sql.*;
import java.util.Properties;
import domain.model.Person;
import domain.model.Product;
import jdk.nashorn.internal.scripts.JD;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;

public class DBMain {
    public static void main(String[] args) throws SQLException, InterruptedException  {
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX35?currentSchema=r0593798";
        properties.setProperty("user", "local_r0593798");
        properties.setProperty("password", "xuR-5FCAj=AxUQc");

        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");

        Connection connection = DriverManager.getConnection(url,properties);
        Statement statement = connection.createStatement();
        Person newPerson = new Person();

        String userId = "";
        while(true) {
            userId = JOptionPane.showInputDialog("User id: ");
            try {
                newPerson.setUserid(userId);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                JOptionPane optionPane = new JOptionPane("Please enter a user id", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Failure");
                dialog.setVisible(true);
            }
        }

        String firstName = JOptionPane.showInputDialog("First name: ");
        try {
            newPerson.setFirstName(firstName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        String lastName = JOptionPane.showInputDialog("Last name: ");
        try {
            newPerson.setLastName(lastName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        String email = JOptionPane.showInputDialog("Email:");
        try {
            newPerson.setEmail(email);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        String password = JOptionPane.showInputDialog("Password: ");
        try {
            newPerson.setPassword(password);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO person (firstname,lastname,userid,email,password) VALUES (?,?,?,?,?)");
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, userId);
        pstmt.setString(4, email);
        pstmt.setString(5, password);
        pstmt.executeUpdate();
        ResultSet result = statement.executeQuery( "SELECT * FROM person");
        while(result.next()){
            String voornaam = result.getString("firstname");
            String achternaam = result.getString("lastname");
            String naam = voornaam + " " + achternaam;
            String userid = result.getString("userid");
            email = result.getString("email");
            System.out.println(naam + ": " + userid + ", " + email);
        }
        statement.close();
        connection.close();
    }
}

package domain.model;

public class Book {

    private String title;
    private String author;
    private String authorFirstName;
    private String authorLastName;
    private double price;

    public Book(String title, String author, double price) {
        setTitle(title);
        setAuthor(author);
        setPrice(price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        String[] fullName = author.split(" ");
        this.setAuthorFirstName(fullName[0]);
        this.setAuthorLastName(fullName[1]);
    }

    private void setAuthorFirstName(String s) {
        this.authorFirstName = s;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Your price should be a positive integer");
        }
        this.price = price;
    }
}

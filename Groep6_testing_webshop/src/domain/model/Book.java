package domain.model;

public class Book {

    private String title;
    private String author;
    private String authorFirstName;
    private String authorLastName;

    public Book(String title, String author) {
        setTitle(title);
        setAuthor(author);
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
}

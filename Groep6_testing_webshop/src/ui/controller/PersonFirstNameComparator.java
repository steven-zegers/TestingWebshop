package ui.controller;

import domain.model.Book;
import domain.model.Person;

import java.util.Comparator;

public class PersonFirstNameComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthorFirstName().toLowerCase().compareTo(b2.getAuthorFirstName().toLowerCase());
    }
}

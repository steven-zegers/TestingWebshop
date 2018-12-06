package ui.controller;

import domain.model.Book;

import java.util.Comparator;

public class BookTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
    }
}

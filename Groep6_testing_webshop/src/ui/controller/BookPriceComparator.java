package ui.controller;

import domain.model.Book;

import java.util.Comparator;

public class BookPriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if(o1.getPrice() < o2.getPrice()) return -1;
        if(o1.getPrice() > o2.getPrice()) return 1;
        return 0;
    }
}

package ui.controller;

import domain.model.Person;

import java.util.Comparator;

public class PersonFirstNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getFirstName().toLowerCase().compareTo(o2.getFirstName().toLowerCase());
    }
}

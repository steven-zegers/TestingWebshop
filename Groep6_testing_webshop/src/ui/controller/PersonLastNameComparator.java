package ui.controller;

import domain.model.Person;

import java.util.Comparator;

public class PersonLastNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getLastName().toLowerCase().compareTo(o2.getLastName().toLowerCase());
    }
}

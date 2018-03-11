package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Nikolay", "Meleshkin", "MyPhone", "Engels"));
        List<Person> persons = phones.find("Nikolay");
        assertThat(persons.iterator().next().getSurname(), is("Meleshkin"));
    }
}
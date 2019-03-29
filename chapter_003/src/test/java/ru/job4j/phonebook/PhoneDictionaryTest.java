package ru.job4j.phonebook;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PhoneDictionaryTest {

    private PhoneDictionary dictionary = new PhoneDictionary();

    @Test
    public void phoneDictionaryTest() {
        dictionary.add(new Person("name_1", "surname_1", "123456", "adress_1"));
        dictionary.add(new Person("name_2", "surname_2", "654321", "adress_2"));
        dictionary.add(new Person("name_3", "surname_3", "123654", "adress_3"));
        dictionary.add(new Person("name_1", "surname_2", "123456", "adress_3"));

        List<Person> result = dictionary.find("name_1");

        assertThat(result.get(0).getSurname(), is("surname_1"));
        assertThat(result.get(1).getSurname(), is("surname_2"));
    }

}
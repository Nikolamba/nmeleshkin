package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    private UserStore userStore = new UserStore();

    @Test
    public void whenUseAddReplaceDeleteElements() {
        userStore.add(new User("123"));
        userStore.add(new User("456"));
        userStore.add(new User("789"));
        assertThat(userStore.replace("123", new User("789")), is(true));
        assertThat(userStore.delete("456"), is(true));
        assertThat(userStore.replace("000", new User("111")), is(false));
        assertThat(userStore.delete("123"), is(false));
    }

    @Test
    public void whenDeleteFromEmptyStore() {
        assertThat(userStore.delete("111"), is(false));
    }
}
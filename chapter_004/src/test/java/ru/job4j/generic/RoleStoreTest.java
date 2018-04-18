package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    private RoleStore roleStore = new RoleStore();

    @Test
    public void whenUseAddReplaceDeleteElements() {
        roleStore.add(new Role("123"));
        roleStore.add(new Role("456"));
        roleStore.add(new Role("789"));
        assertThat(roleStore.replace("123", new Role("789")), is(true));
        assertThat(roleStore.delete("456"), is(true));
        assertThat(roleStore.replace("000", new Role("111")), is(false));
        assertThat(roleStore.delete("123"), is(false));
    }

    @Test
    public void whenDeleteFromEmptyStore() {
        assertThat(roleStore.delete("111"), is(false));
    }
}
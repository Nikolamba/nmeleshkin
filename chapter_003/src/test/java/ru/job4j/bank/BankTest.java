package ru.job4j.bank;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class BankTest {
    private Bank bank = new Bank();

    @Test
    public void whenUseAddUser() {
        bank.addUser(new User("Nikolay", "123"));
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("Nikolay", "123"), new ArrayList<>());
        assertThat(bank.getClients(), is(expected));
    }

    @Test
    public void whenDualLoadUser() {
        bank.addUser(new User("Nikolay", "123"));
        bank.addUser(new User("Viktor", "123"));
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("Nikolay", "123"), new ArrayList<>());
        assertThat(bank.getClients(), is(expected));
    }

    @Test
    public void whenUseDeleteUser() {
        bank.addUser(new User("Nikolay", "123"));
        bank.addUser(new User("Viktor", "456"));
        bank.deleteUser(new User("Nikolay", "123"));
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("Viktor", "456"), new ArrayList<>());
        assertThat(bank.getClients(), is(expected));
    }

    @Test
    public void whenUseAddAccountToUser() {
        bank.addUser(new User("Nikolay", "123"));
        bank.addAccountToUser("123", new Account(0, "09876"));
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("Nikolay", "123"),
                new ArrayList<>(Arrays.asList(new Account(0, "09876"))));
        assertThat(bank.getClients(), is(expected));
    }

    @Test
    public void whenUseDeleteAccountFromUser() {
        bank.addUser(new User("Nikolay", "123"));
        bank.addAccountToUser("123", new Account(0, "98765"));
        bank.deleteAccountFromUser("123", new Account(0, "98765"));
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("Nikolay", "123"), new ArrayList<>());
        assertThat(bank.getClients(), is(expected));
    }


    @Test
    public void whenUseGetUserAccounts() {
        bank.addUser(new User("Nikolay", "123"));
        bank.addAccountToUser("123", new Account(0, "98765"));
        bank.addAccountToUser("123", new Account(0, "43210"));
        List<Account> expected = new ArrayList<>(Arrays.asList(new Account(0, "98765"),
                new Account(0, "43210")));
        assertThat(bank.getUserAccounts("123"), is(expected));
    }

    @Test
    public void whenUseTransferMoney() {
        bank.addUser(new User("Nikolay", "123"));
        bank.addAccountToUser("123", new Account(1500, "98765"));
        bank.addUser(new User("Viktor", "456"));
        bank.addAccountToUser("456", new Account(0, "43210"));
        bank.transferMoney("123", "98765", "456", "43210", 1000);
        double amountUs1 = bank.getUserAccounts("123").get(0).getValue();
        double amountUs2 = bank.getUserAccounts("456").get(0).getValue();
        assertThat(amountUs1, is(500d));
        assertThat(amountUs2, is(1000d));
    }
}
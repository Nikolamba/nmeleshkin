package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс содержит информацию о клиентах и их счетах
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Bank {
    //карта, содержащая сведения о клиентах и клиентских счетах
    private Map<User, List<Account>> clients = new HashMap<>();

    /**
     * Добавляет указанного пользователя в список клиентов
     * @param user добавляемый пользователь
     */
    public void addUser(User user) {
        clients.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Удаляет указанного пользователя из списка клиентов
     * @param user удаляемый пользователь
     */
    public void deleteUser(User user) {
        clients.remove(user);
    }

    /**
     * Добавляет пользователю новый счет
     * @param passport паспорт пользователя
     * @param account счет, который необходимо добавить пользователю
     */
    public void addAccountToUser(String passport, Account account) {
        if (clients.containsKey(new User("", passport))) {
               clients.get(new User("", passport)).add(account);
        }
    }

    /**
     * удаляет счет у пользователя
     * @param passport паспорт пользователя
     * @param account счет, который необходимо удалить у пользователя
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (clients.containsKey(new User("", passport))) {
            clients.get(new User("", passport)).remove(account);
        }
    }

    /**
     * возвращает список всех счетов у пользователя
     * @param passport паспорт клиента
     * @return список счетов пользователя
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = null;
        if (clients.containsKey(new User("", passport))) {
            result = clients.get(new User("", passport));
        }
        return result;
    }

    //осуществляет перевод с одного счета на другой
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean result = false;

        if (this.clients.containsKey(new User("", srcPassport))
                && this.clients.containsKey(new User("", destPassport))
                && this.getUserAccounts(srcPassport).contains(new Account(0, srcRequisite))
                && this.getUserAccounts(destPassport).contains(new Account(0, destRequisite))
                && this.getActualAccount(srcPassport, srcRequisite).getValue() >= amount) {

            this.getActualAccount(srcPassport, srcRequisite).reduceBy(amount);
            this.getActualAccount(destPassport, destRequisite).increaseBy(amount);
            result = true;
        }

        return result;
    }

    /**
     * возвращает счет пользователя, удовлетворяющий условиям
     * @param passport паспорт клиента
     * @param requisite реквизиты счета
     * @return возвращает найденный счет, null - в противном случае
     */
    private Account getActualAccount(String passport, String requisite) {
        User client = new User("", passport);
        Account account = new Account(0, requisite);
        List<Account> list = this.clients.get(client);
        return list.get(list.indexOf(account));
    }

    //возвращает все клиентов
    public Map<User, List<Account>> getClients() {
        return this.clients;
    }
}

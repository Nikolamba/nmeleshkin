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
        User client = new User("", passport);
        if (clients.containsKey(client)) {
               clients.get(client).add(account);
        }
    }

    /**
     * удаляет счет у пользователя
     * @param passport паспорт пользователя
     * @param account счет, который необходимо удалить у пользователя
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User client = new User("", passport);
        if (clients.containsKey(client)) {
            clients.get(client).remove(account);
        }
    }

    /**
     * возвращает список всех счетов у пользователя
     * @param passport паспорт клиента
     * @return список счетов пользователя
     */
    public List<Account> getUserAccounts(String passport) {
        User client = new User("", passport);
        List<Account> result = null;
        if (clients.containsKey(client)) {
            result = clients.get(client);
        }
        return result;
    }

    //осуществляет перевод с одного счета на другой
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean result = false;

        User us1 = new User("", srcPassport);
        User us2 = new User("", destPassport);
        Account accUs1 = new Account(0, srcRequisite);
        Account accUs2 = new Account(0, destRequisite);

        if (this.clients.containsKey(us1)
                && this.clients.containsKey(us2)
                && this.clients.get(us1).contains(accUs1)
                && this.clients.get(us2).contains(accUs2)
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

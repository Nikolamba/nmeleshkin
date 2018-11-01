package ru.job4j.hallservice;

import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public interface Operations {
    boolean addAccount(int row, int place, Account account);
    boolean deleteAccount(int row, int place);
    boolean editAccount(int row, int place, Account newAccount);
    boolean isTaken(int row, int place);
    List<Place> getAllPlaces();
    Account findAccountById(int accountId);
}

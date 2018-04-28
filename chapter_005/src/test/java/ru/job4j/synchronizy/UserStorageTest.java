package ru.job4j.synchronizy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class UserStorageTest {

    private UserStorage userStorage = new UserStorage();

    class ThreadAddUser implements Runnable {
        UserStorage us;

        ThreadAddUser(UserStorage us) {
            this.us = us;
        }

        @Override
        public void run() {
            List<Boolean> listResultAdd = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                listResultAdd.add(us.add(new User(i, 100)));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            System.out.println(listResultAdd);
        }
    }

    class ThreadTransferUserToUser implements Runnable {
        UserStorage us;

        ThreadTransferUserToUser(UserStorage us) {
            this.us = us;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                us.transfer(1, 2, 1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenThreadsAddUsersInSameTime() throws InterruptedException {
        Thread t1 = new Thread(new ThreadAddUser(userStorage));
        Thread t2 = new Thread(new ThreadAddUser(userStorage));
        Thread t3 = new Thread(new ThreadAddUser(userStorage));
        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();
    }

    @Test
    public void whenThreadsTransferAmount() throws InterruptedException {
        userStorage.add(new User(1, 15));
        userStorage.add(new User(2, 0));
        Thread t1 = new Thread(new ThreadTransferUserToUser(userStorage));
        Thread t2 = new Thread(new ThreadTransferUserToUser(userStorage));
        Thread t3 = new Thread(new ThreadTransferUserToUser(userStorage));
        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();
        System.out.println("Amount of User0 = " + userStorage.getMountById(1));
        System.out.println("Amount of User1 = " + userStorage.getMountById(2));
    }
}
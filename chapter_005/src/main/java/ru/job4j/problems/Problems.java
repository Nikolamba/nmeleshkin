package ru.job4j.problems;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
class CommonObject {
    int counter = 0;

    public void increment() {
        this.counter++;
    }
}

class CounterThread implements Runnable {
    private CommonObject res;
    CounterThread(CommonObject res) {
        this.res = res;
    }

    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            //System.out.printf("'%s' - %d\n", Thread.currentThread().getName(), res.counter);
            res.increment();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //смотрим итоговое значение общего объекта
        System.out.printf("Итоговое значение: '%s' - %d\n", Thread.currentThread().getName(), res.counter);
    }
}
public class Problems {
    public static void main(String[] args) {
        //создаем общий объект ссылочного типа
        CommonObject commonObject = new CommonObject();
        for (int i = 1; i < 6; i++) {
            //и передаем его каждому потоку
            Thread t = new Thread(new CounterThread(commonObject));
            t.setName("Поток " + i);
            t.start();
        }
    }
}

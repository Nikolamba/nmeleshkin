package ru.job4j.strategy;

/**
 * Класс для вывода фигур на консоль
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 */
public class Paint {
    /**
     * функция для вывода фигуры на консоль
     * @param shape фигура для вывода
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        new Paint().draw(new Square());
        new Paint().draw(new Triangle());
    }
}

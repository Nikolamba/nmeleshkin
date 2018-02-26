package ru.job4j.strategy;

/**
 * Класс для прорисовки треугольника
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 */
public class Triangle implements Shape {
    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("  +  \n");
        sb.append(" +++ \n");
        sb.append("+++++");
        return sb.toString();
    }
}

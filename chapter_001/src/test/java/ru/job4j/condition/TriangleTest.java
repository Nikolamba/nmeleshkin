package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = 2D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    //в случае, если 2 точки треугольника равны
    public void whenTwoPointsEqual() {
        //создаем три точки, 2 из которых равны
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        Point c = new Point(2, 2);
        //создаем объект треугольник
        Triangle triangle = new Triangle(a, b, c);
        //вычисляем площадь
        double result = triangle.area();
        //задаем ожидаемый результат
        double expected = -1;
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    //в случае, если введены отрицательные значения координат точек
    public void whenNegativeCoordinates() {
        Point a = new Point(0, 0);
        Point b = new Point(0, -2);
        Point c = new Point(-2, 0);
        //создаем объект треугольник
        Triangle triangle = new Triangle(a, b, c);
        //вычисляем площадь
        double result = triangle.area();
        //задаем ожидаемый результат
        double expected = 2;
        assertThat(result, closeTo(expected, 0.1));
    }
}
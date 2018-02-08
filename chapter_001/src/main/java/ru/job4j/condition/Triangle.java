package ru.job4j.condition;

public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    public double area() {
        double rsl = -1;
        //вычисляем длину каждой стороны треугольника
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        //проверка условия возможности построения треугольника
        if (this.exist(ab, ac, bc)) {
            //вычисляем площадь
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    private boolean exist(double ab, double ac, double bc) {
        //если длина любой из сторон треугольника == 0, то тругольник построить нельзя
        //отрицательные координаты разрешены
        if (ab == 0 || ac == 0 || bc == 0) {
            return false;
        }
        return true;
    }
}

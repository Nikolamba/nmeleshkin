package ru.job4j.loop;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Board {
    /**
     * метод рисует шахматную доску из символов 'Х' и ' '
     * @param width ширина доски
     * @param height высота доски
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
        }
        return screen.toString();
    }
}


package ru.job4j.chess;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
class FigureNotFoundException extends RuntimeException {
    FigureNotFoundException(String msg) {
        super(msg);
    }
}

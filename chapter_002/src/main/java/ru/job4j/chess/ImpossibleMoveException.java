package ru.job4j.chess;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
class ImpossibleMoveException extends RuntimeException {
    ImpossibleMoveException(String msg) {
        super(msg);
    }
}

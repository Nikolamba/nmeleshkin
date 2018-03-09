package ru.job4j.chess;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
class OccupiedWayException extends RuntimeException {
    OccupiedWayException(String msg) {
        super(msg);
    }
}

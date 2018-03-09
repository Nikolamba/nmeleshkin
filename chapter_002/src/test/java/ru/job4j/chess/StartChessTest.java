package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StartChessTest {

    private Board board = new Board();
    private PrintStream stdOut = new PrintStream(System.out);
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOut() {
        System.setOut(stdOut);
    }

    @Test
    public void whenUserAddFigure() {
        Input input = new StubInput(new String[] {"0", "0", "0", "2"});
        new StartChess(input, board).init();
        assertThat(board.getCell(0, 0).isBusy(), is(true));
        assertThat(board.getCountFigures(), is(1));
    }

    @Test
    public void whenUserAddFigureOccupiedWay() {
        Input input = new StubInput(new String[] {"0", "3", "3", "0", "3", "3", "2"});
        new StartChess(input, board).init();
        assertThat(out.toString(), containsString("Клетка занята. Повторите попытку"));
    }

    @Test
    public void whenUserMoveFigureCorrect() {
        Input input = new StubInput(new String[] {"0", "0", "0", "1", "0", "0", "0", "7", "2"});
        new StartChess(input, board).init();
        assertThat(board.getCell(0, 7).isBusy(), is(true));
        assertThat(board.getCell(0, 0).isBusy(), is(false));
        assertThat(board.getCountFigures(), is(1));
    }

    @Test
    public void whenUserMoveFigureNotFound() {
        Input input = new StubInput(new String[] {"0", "0", "0", "1", "1", "1", "0", "7", "2"});
        new StartChess(input, board).init();
        assertThat(out.toString(), containsString("Нет фигуры для перемещения!"));
    }

    @Test
    public void whenUserMoveFigureInposibleMove() {
        Input input = new StubInput(new String[] {"0", "7", "7", "1", "7", "7", "2", "6", "2"});
        new StartChess(input, board).init();
        assertThat(out.toString(), containsString("Фигура не может так ходить!"));
    }

    @Test
    public void whenUserMoveFigureOccupiedWay() {
        Input input = new StubInput(new String[] {"0", "1", "2", "0", "3", "2", "1", "1", "2", "6", "2", "2"});
        new StartChess(input, board).init();
        assertThat(out.toString(), containsString("Путь для фигуры занят!"));
        assertThat(board.getCountFigures(), is(2));
    }
}
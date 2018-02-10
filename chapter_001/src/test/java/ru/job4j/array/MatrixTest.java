package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class MatrixTest {
    private Matrix matrix = new Matrix();

    @Test
    //когда размер матрицы = 5
    public void whenSizeMatrix5() {
        int[][] data = matrix.multiple(5);
        int[][] expectedData = new int[][]{{1, 2, 3, 4, 5},
                                           {2, 4, 6, 8, 10},
                                           {3, 6, 9, 12, 15},
                                           {4, 8, 12, 16, 20},
                                           {5, 10, 15, 20, 25}};
        assertThat(data, is(expectedData));
    }

    @Test
    //когда размер матрицы = 3
    public void whenSizeMatrix3() {
        int[][] data = matrix.multiple(3);
        int[][] expectedData = new int[][]{{1, 2, 3},
                                           {2, 4, 6},
                                           {3, 6, 9}};
        assertThat(data, is(expectedData));
    }
}
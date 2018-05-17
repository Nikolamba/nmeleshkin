package ru.job4j.wait;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class ParallelSearchTest {

    @Test
    public void whenSearchFilesShouldReturnResult() throws InterruptedException {
        String root = "C:\\Users\\Kalampus\\Desktop";
        String text = "1";
        List<String> exts = new ArrayList<>(Arrays.asList(".rtf", ".txt"));
        ParallelSearch search = new ParallelSearch(root, text, exts);
        search.init();
        List<String> list = search.result();
        for (String str : list) {
            System.out.println(str);
        }
    }
}
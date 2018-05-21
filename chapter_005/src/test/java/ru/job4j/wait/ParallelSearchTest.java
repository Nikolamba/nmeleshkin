package ru.job4j.wait;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

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
        Queue<String> list = search.result();
        while (!list.isEmpty()){
            System.out.println(list.poll());
        }
    }
}
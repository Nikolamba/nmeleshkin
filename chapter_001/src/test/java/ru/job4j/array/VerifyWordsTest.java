package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * класс для тестирования VerifyWords
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class VerifyWordsTest {
    private VerifyWords verifyWords = new VerifyWords();

    @Test
    public void whenStringNotContainsString() {
        String origin = "Привет!";
        String sub = "Хай";
        assertThat(false, is(verifyWords.contains(origin, sub)));
    }

    @Test
    public void whenStirngContainsString() {
        String origin = "Добрый день";
        String sub = "день";
        assertThat(true, is(verifyWords.contains(origin, sub)));
    }

}
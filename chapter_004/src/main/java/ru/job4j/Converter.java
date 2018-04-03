package ru.job4j;

import java.util.Iterator;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            Iterator<Integer> currentIt = it.next();

            @Override
            public boolean hasNext() {
                boolean result = true;
                if (!currentIt.hasNext() && it.hasNext()) {
                    currentIt = it.next();
                    result = currentIt.hasNext();
                } else if (currentIt.hasNext()) {
                    result = true;
                } else if (!currentIt.hasNext() && !it.hasNext()) {
                    result = false;
                }
                return result;
            }

            @Override
            public Integer next() {
                Integer result;
                if (currentIt.hasNext()) {
                    result = currentIt.next();
                } else {
                    currentIt = it.next();
                    result = currentIt.next();
                }
                return result;
            }

        };
    }
}

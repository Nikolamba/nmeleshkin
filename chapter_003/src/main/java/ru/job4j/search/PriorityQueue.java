package ru.job4j.search;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Класс, реализующий очередь заявок, отсортированный по приоритету
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Добавляет задачу в очередь, учитывая приоритет задачи
     * @param task задача, которую нужно добавить
     */
    public void put(Task task) {
        int index = 0;
        if (tasks.isEmpty()) {
            tasks.add(task);
        }

        for (Task localTask : tasks) {
            if (localTask.getPriority() < task.getPriority()) {
                tasks.add(index, task);
                break;
            }
            index++;
        }
    }

    /**
     * Извлекает задачу с наивысшим приоритетом
     * @return возвращает задачу с наивысшим приоритетом в очереди
     */
    public Task take() {
        return this.tasks.poll();
    }
}

package ru.job4j.list;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * @param <T> тип данных, содержащихся в узле
 */
public class Node<T> {
    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
        this.next = null;
    }

    boolean hasCycle(Node first) {
        boolean result = false;
        Node slow = first;
        Node fast = first.next;

            while (fast != null) {
            if (fast == slow) {
                result = true;
                break;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                result = false;
                break;
            }
            fast = fast.next;
        }
        return result;
    }
}

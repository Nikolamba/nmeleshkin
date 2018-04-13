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
        Node slow = first;
        Node fast = first.next;
        while(fast != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                return false;
            }
            fast = fast.next;
        }
        return false;
    }
}

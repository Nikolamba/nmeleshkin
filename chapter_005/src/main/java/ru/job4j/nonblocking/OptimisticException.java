package ru.job4j.nonblocking;

class OptimisticException extends RuntimeException {
    OptimisticException(String message) {
        System.out.println(message);

    }

}

package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int delta = 1;
        while (true) {
            this.rect.setX(this.rect.getX() + delta);
            if (this.rect.getX() + this.rect.getWidth() == 300) {
                delta = -1;
            } else if (this.rect.getX() == 0) {
                delta = 1;
            }
            if (Thread.interrupted()) {
                break;
            }
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

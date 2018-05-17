package ru.job4j.wait;

import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;

    private volatile boolean finish = false;
    private final Queue<String> files = new ConcurrentLinkedQueue<>();
    private final List<String> paths = new CopyOnWriteArrayList<>();

    ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public List<String> result() {
        return this.paths;
    }

    public void init() throws InterruptedException {

        Thread search = new Thread() {
            Path startPath = Paths.get(root);
            @Override
            public void run() {
                try {
                    Files.walkFileTree(startPath, new MyFileVisitor(exts));
                    finish = true;
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        };

        Thread read = new Thread(() -> {
            while (true) {
                synchronized (ParallelSearch.this) {
                    if (finish && files.isEmpty()) {
                        break;
                    }
                    while (files.isEmpty()) {
                        try {
                            ParallelSearch.this.wait();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    readFile(files.poll());
                }
            }
        });

        search.start(); read.start();
        search.join(); read.join();
    }

    private void readFile(String name) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                //System.out.println(buffer);
                if (buffer.contains(text)) {
                    paths.add(name);
                    break;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private class MyFileVisitor extends SimpleFileVisitor<Path> {

        private PathMatcher matcher;

        MyFileVisitor(List<String> exts) {
            StringBuilder string = new StringBuilder();
            string.append("glob:*{");
            for (String str : exts) {
                string.append(str);
                string.append(',');
            }
            string.deleteCharAt(string.length() - 1);
            string.append("}");

            try {
                this.matcher = FileSystems.getDefault().getPathMatcher(string.toString());
            } catch (IllegalArgumentException iae) {
                iae.printStackTrace();
            }
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Path name = file.getFileName();
            if (this.matcher.matches(name)) {
                files.offer(file.toString());
                synchronized (ParallelSearch.this) {
                    ParallelSearch.this.notify();
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

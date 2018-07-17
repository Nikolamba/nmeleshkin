package ru.job4j.wait;

import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.*;

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
    private final LinkedBlockingQueue<String> files = new LinkedBlockingQueue<>();
    private final Queue<String> paths = new ConcurrentLinkedQueue<>();

    ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public Queue<String> result() {
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
            while (!(finish && files.isEmpty())) {
                try {
                    String file = this.files.poll(50L, TimeUnit.MILLISECONDS);
                    if (file != null) {
                        readFile(file);
                    }
                } catch (InterruptedException | IOException exc) {
                    exc.printStackTrace();
                }
            }
        });

        search.start(); read.start();
        search.join(); read.join();
    }

    private void readFile(String name) throws IOException {
        BufferedReader reader = null;
        String buffer;
        try {
            reader = new BufferedReader(new FileReader(name));
            while ((buffer = reader.readLine()) != null) {
                //System.out.println(buffer);
                if (buffer.contains(text)) {
                    paths.offer(name);
                    break;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }

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
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

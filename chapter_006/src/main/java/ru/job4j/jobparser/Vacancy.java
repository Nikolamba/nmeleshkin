package ru.job4j.jobparser;

import java.time.LocalDateTime;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Vacancy {

    private String url;
    private String desc;
    private LocalDateTime data;

    Vacancy(String url, String desc, LocalDateTime data) {
        this.url = url;
        this.desc = desc;
        this.data = data;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDesc() {
        return this.desc;
    }

    public LocalDateTime getData() {
        return this.data;
    }
}

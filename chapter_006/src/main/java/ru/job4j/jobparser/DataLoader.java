package ru.job4j.jobparser;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
class DataLoader {

    private static final Logger LOGGER = LogManager.getLogger("myLogger");
    private LocalDateTime startDate;
    private int nextPage = 2;

    DataLoader(String url, InitSQL sql, LocalDateTime lastDateTime) {
        if (lastDateTime == null) {
            this.startDate = LocalDateTime.of(2018, 1, 1, 0, 0);
        } else {
            this.startDate = lastDateTime;
        }
        fillVacancy(url, sql);
    }

    private void fillVacancy(String url, InitSQL sql) {
        boolean enough = false;
        try {
            LOGGER.info("Work with the resource: " + url);
            Document document = Jsoup.connect(url).get();
            Element table = document.select("table").get(2);
            Elements rows = table.select("tr");

            //добавляем вакансии с текущей страницы
            for (int i = 4; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");
                Elements references = cols.get(1).select("a");
                LocalDateTime dateTimeOfVacancy = getDate(cols.get(5).html());

                enough = !startDate.isBefore(dateTimeOfVacancy);
                if (!enough && this.checkText(references.html())) {
                    sql.add(new Vacancy(references.attr("href"),
                            references.html(),
                            dateTimeOfVacancy));
                }
            }
            //если нужно еще, то переходим на следующую страницу
            if (!enough) {
                table = document.select("table").get(3);
                Elements references = table.select("a");
                for (Element ref : references) {
                    int numPage = Integer.valueOf(ref.html());
                    if (numPage == this.nextPage) {
                        this.nextPage++;
                        this.fillVacancy(ref.attr("href"), sql);
                    }
                }
            }
            LOGGER.info("Work with the resource " + url + " finished");
        } catch (IOException e) {
            LOGGER.error("Error connecting to web resource", e);
        }
    }

    private boolean checkText(final String text) {
        boolean result = false;
        String textToLowerCase = text.toLowerCase();

        if (textToLowerCase.contains("java") && !textToLowerCase.contains("javascript")
                && !textToLowerCase.contains("java script")) {
            result = true;
        }
        return result;
    }

    //парсим дату вакансии
    private LocalDateTime getDate(String str) {
        String[] splitDate = str.split(" ");
        LocalDateTime dateTime;

        if (str.contains("сегодня")) {
            dateTime = this.getTodayDate(splitDate);
        } else if (str.contains("вчера")) {
            dateTime = this.getTodayDate(splitDate);
            dateTime = dateTime.minusDays(1);
        } else {
            String[] splitTime = splitDate[3].split(":");
            dateTime = LocalDateTime.of(Integer.valueOf(splitDate[2].substring(0, 2)) + 2000,
                    getMonths(splitDate[1]),
                    Integer.valueOf(splitDate[0]),
                    Integer.valueOf(splitTime[0]),
                    Integer.valueOf(splitTime[1]));
        }
        return dateTime;
    }

    private LocalDateTime getTodayDate(String[] dateTime) {
        dateTime = dateTime[1].split(":");
        return LocalDateTime.now()
                .withHour(Integer.valueOf(dateTime[0]))
                .withMinute(Integer.valueOf(dateTime[1]));
    }

    private int getMonths(String str) {
        String[] strings = {
            "янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"};
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(str)) {
                return i + 1;
            }
        }
        return -1;
    }
}

package ru.job4j.jobparser;

import org.quartz.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StartApp implements Job {

    private static final Logger LOGGER = LogManager.getLogger("myLogger");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String urlForParsing = "http://www.sql.ru/forum/job-offers";
        Properties config = new Properties();
        LocalDateTime lastDateTime;

        try {
            config.load(StartApp.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            LOGGER.error("Error getting settings app.properties", e);
        }

        //инициализируем базу данных
        try (InitSQL initSQL = new InitSQL(config)) {
            //получаем наибольшу дату в БД
            lastDateTime = initSQL.getLastDataTime();
            //получаем и загружаем данные в базу данных
            new DataLoader(urlForParsing, initSQL, lastDateTime);
            //удаляем дубликаты
            initSQL.removeDuplicates();
        } catch (Exception e) {
            LOGGER.error("Error with the database", e);
        }
    }

    public static void main(String[] args) {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = newJob(StartApp.class)
                    .withIdentity("parserSQL", "group1")
                    .build();

            CronTrigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(cronSchedule("0 0 12 * * ?"))
                    .forJob("parserSQL", "group1")
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            LOGGER.info("job started");

        } catch (SchedulerException e) {
            LOGGER.error(e);
        }
    }
}

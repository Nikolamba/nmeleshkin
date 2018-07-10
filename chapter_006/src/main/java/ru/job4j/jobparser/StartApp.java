package ru.job4j.jobparser;

import org.apache.logging.log4j.MarkerManager;
import org.quartz.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import org.apache.logging.log4j.Marker;
import org.quartz.impl.StdSchedulerFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StartApp implements Job {

    private static final Logger LOGGER = LogManager.getLogger(StartApp.class);
    private static final Marker INFO_MARKER = MarkerManager.getMarker("info");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("error");
    private static final Properties CONFIG = new Properties();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String urlForParsing = "http://www.sql.ru/forum/job-offers";
        LocalDateTime lastDateTime;
        try (InitSQL initSQL = new InitSQL(CONFIG)) {
            lastDateTime = initSQL.getLastDataTime();
            new DataLoader(urlForParsing, initSQL, lastDateTime);
            initSQL.removeDuplicates();
        } catch (Exception e) {
            LOGGER.error(ERROR_MARKER, "Error with the database", e);
        }
    }

    public static void main(String[] args) {
        try {
            CONFIG.load(StartApp.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            LOGGER.error(ERROR_MARKER, "Error getting settings app.properties", e);
        }
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = newJob(StartApp.class)
                    .withIdentity("parserSQL", "group1")
                    .build();

            CronTrigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(cronSchedule(CONFIG.getProperty("cron.time")))
                    .forJob("parserSQL", "group1")
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            LOGGER.info(INFO_MARKER, "job started");
        } catch (SchedulerException e) {
            LOGGER.error(ERROR_MARKER, "Error", e);
        }
    }
}

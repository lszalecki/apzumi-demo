package com.example.apzumidemo;


import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200
 */

@Configuration
public class SchedulerConfig
{
    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.quartz.start.hour}")
    private int hour;

    @Value("${spring.quartz.start.minute}")
    private int minute;


    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        SpringJobFactory jobFactory = new SpringJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(GetPostsJob.class)
                .storeDurably()
                .withIdentity("getAllPosts", "group1")
                .withDescription("Get all posts from URL.")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("getAllPostsTrigger" , "group1")
                .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                        .onEveryDay().startingDailyAt(TimeOfDay.hourAndMinuteOfDay(hour, minute)).endingDailyAfterCount(1)).build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        factory.setSchedulerName("My Scheduler");
        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory(applicationContext));
        return factory;
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException
    {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job, trigger);

        scheduler.start();
        return scheduler;
    }

}

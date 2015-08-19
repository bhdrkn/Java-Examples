package com.bahadirakin;

import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class SchedulerController {

    @Autowired
    Scheduler scheduler;

    public void addJob(DatabaseMonitor databaseMonitor) {

        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("sqlQuery", databaseMonitor.getSqlQuery());
        final JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setName(databaseMonitor.getName());
        jobDetail.setDurability(true);
        jobDetail.setJobDataMap(jobDataMap);
        jobDetail.setJobClass(DatabaseJob.class);

        try {
            final CronTriggerImpl trigger = new CronTriggerImpl();
            trigger.setCronExpression("0/2 * * * * ?");
            trigger.setName(databaseMonitor.getName());
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ParseException | SchedulerException e) {
            e.printStackTrace();
        }
    }
}


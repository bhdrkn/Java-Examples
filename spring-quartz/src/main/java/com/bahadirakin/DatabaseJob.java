package com.bahadirakin;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

public class DatabaseJob implements Job, Serializable {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        final JobDataMap jobDataMap = context.getMergedJobDataMap();
        final String sqlQuery = jobDataMap.getString("sqlQuery");
        System.out.println("SqlQuery: " + sqlQuery);
    }
}
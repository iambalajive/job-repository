package com.balaji.jobs;

import com.balaji.jobs.configuration.DbConfig;
import com.balaji.jobs.configuration.JobRepositoryConfiguration;
import com.balaji.jobs.dao.*;
import com.balaji.jobs.health.TemplateHealthCheck;
import com.balaji.jobs.resources.EvntResource;
import com.balaji.jobs.resources.TriggerJobResource;
import com.balaji.jobs.resources.JobResource;
import com.balaji.jobs.scheduler.ConcurrentStatusUpdater;
import com.balaji.jobs.scheduler.DBPoller;
import com.balaji.jobs.scheduler.Scheduler;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.Timer;

public class JobConfigurationService extends Service<JobRepositoryConfiguration> {
    public static void main(String[] args) throws Exception {
        new JobConfigurationService().run(args);
    }

    private JobConfigurationService() {
        super("job-repository");
    }

    @Override
    protected void initialize(JobRepositoryConfiguration configuration,
                              Environment environment) {

        DbConfig dbConfig = configuration.getDbConfig();
        String jdbcUrl = "jdbc:postgresql://" + dbConfig.getHost() + ":" + dbConfig.getPort() + "/" + dbConfig.getDbName();

        DBI dbi = new DBI(jdbcUrl, dbConfig.getUserName(), dbConfig.getPassword());

        JobDAO jobDAO = new JobDAO(dbi);
        EventDAO eventDAO = new EventDAO(dbi);
        JobScheduleDAO jobScheduleDAO = new JobScheduleDAO(dbi);
        JobConfigDAO jobConfigDAO = new JobConfigDAO(dbi);
        JobExecutionStatusDAO jobExecutionStatusDAO = new JobExecutionStatusDAO(dbi);



        environment.addResource(new JobResource(jobDAO, eventDAO ,jobScheduleDAO,jobConfigDAO));
        environment.addResource(new EvntResource(eventDAO));
        environment.addResource(new TriggerJobResource(eventDAO, jobScheduleDAO));
        environment.addHealthCheck(new TemplateHealthCheck());


        ConcurrentStatusUpdater concurrentStatusUpdater = new ConcurrentStatusUpdater(jobScheduleDAO,jobExecutionStatusDAO);
        Scheduler scheduler = new Scheduler(20,concurrentStatusUpdater);

        Timer timer = new Timer();
        timer.schedule(new DBPoller(jobScheduleDAO,scheduler), 0, 2000);

    }
}

package com.balaji.jobs.scheduler;

import com.balaji.jobs.core.Task;
import com.balaji.jobs.dao.JobScheduleDAO;

import java.util.List;
import java.util.TimerTask;

//DB poller timer thread that polls picks records and sends it to scheduler
public class DBPoller extends TimerTask {

    private JobScheduleDAO jobScheduleDAO;
    private Scheduler scheduler;

    public DBPoller(JobScheduleDAO jobScheduleDAO, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.jobScheduleDAO = jobScheduleDAO;
    }


    @Override
    public void run() {

        List<Task> tasks = jobScheduleDAO.findScheduleLessThanTimestamp(System.currentTimeMillis());

        System.out.println("Polling for tasks "+ tasks.size());
        scheduler.schedule(tasks);
    }
}

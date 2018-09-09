package com.balaji.jobs.scheduler;

import com.balaji.jobs.core.JobSchedule;
import com.balaji.jobs.core.Task;
import com.balaji.jobs.core.TaskMeta;
import com.balaji.jobs.dao.JobExecutionStatusDAO;
import com.balaji.jobs.dao.JobScheduleDAO;
import com.balaji.jobs.utils.TimeUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//A Concurrent Status updater that updates all the Status
// for the given Task
// Mutiple reads are allowed , but while writing no read is allowed
// This is done to prevent double execution of tasks

public class ConcurrentStatusUpdater {

    private JobScheduleDAO jobScheduleDAO;
    private JobExecutionStatusDAO jobExecutionStatusDAO;

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public ConcurrentStatusUpdater(JobScheduleDAO jobScheduleDAO, JobExecutionStatusDAO jobExecutionStatusDAO) {
        this.jobScheduleDAO = jobScheduleDAO;
        this.jobExecutionStatusDAO = jobExecutionStatusDAO;
    }


    public boolean isJobExecutionAlreadyComplete(String jobScheduleId) {
        try {
            readLock.lock();
            List<JobSchedule> jobSchedules = jobScheduleDAO.findJobScheduleById(jobScheduleId);
            return jobSchedules.isEmpty();
        } finally {
            readLock.unlock();
        }

    }

    public void updateStatus(Task task, int jobStatus, long jobStartTime, long jobEndTime) {

        try {
            writeLock.lock();

            TaskMeta taskMeta = task.getTaskMeta();

            //Create a new Schedule for the next run
            if (StringUtils.isNotBlank(taskMeta.getFreqType())) {
                //Add entry for next job run
                jobScheduleDAO.createJobSchedule(taskMeta.getJobId(),
                        TimeUtil.getNextExecutionTimeinMillis(taskMeta.getFreqType(), taskMeta.getFreqVal()));
            }

            //Delete the latest schedule of the task
            jobScheduleDAO.deleteJobSchedule(task.getTaskMeta().getJobScheduleId());


            //Add entry into job execution status table
            jobExecutionStatusDAO.createJobExecutionStatus(taskMeta.getJobId(), 1, jobStartTime, jobEndTime);
        } finally {
            writeLock.unlock();
        }

    }
}

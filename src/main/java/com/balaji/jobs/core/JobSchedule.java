package com.balaji.jobs.core;

public class JobSchedule {
    private String jobScheduleId;

    private String jobId;

    private long nextRunTime;

    public JobSchedule(String jobId, long nextRunTime) {
        this.jobId = jobId;
        this.nextRunTime = nextRunTime;
    }


    public JobSchedule(String jobId, long nextRunTime, String jobScheduleId) {
        this.jobId = jobId;
        this.nextRunTime = nextRunTime;
        this.jobScheduleId = jobScheduleId;
    }


    public String getJobScheduleId() {
        return jobScheduleId;
    }

    public void setJobScheduleId(String jobScheduleId) {
        this.jobScheduleId = jobScheduleId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public long getNextRunTime() {
        return nextRunTime;
    }

    public void setNextRunTime(long nextRunTime) {
        this.nextRunTime = nextRunTime;
    }
}

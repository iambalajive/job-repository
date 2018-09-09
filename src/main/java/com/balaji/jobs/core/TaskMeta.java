package com.balaji.jobs.core;

public class TaskMeta {
    private String jobId;
    private String jobScheduleId;
    private String name;
    private int freqVal;

    public int getFreqVal() {
        return freqVal;
    }

    @Override
    public String toString() {
        return "TaskMeta{" +
                "jobId='" + jobId + '\'' +
                ", jobScheduleId='" + jobScheduleId + '\'' +
                ", name='" + name + '\'' +
                ", freqVal=" + freqVal +
                ", freqType='" + freqType + '\'' +
                '}';
    }

    public void setFreqVal(int freqVal) {
        this.freqVal = freqVal;
    }

    public TaskMeta(String jobId, String jobScheduleId, String name, String freqType, int freqVal) {
        this.jobId = jobId;
        this.jobScheduleId = jobScheduleId;
        this.name = name;
        this.freqType = freqType;
        this.freqVal = freqVal;

    }

    private String freqType;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobScheduleId() {
        return jobScheduleId;
    }

    public void setJobScheduleId(String jobScheduleId) {
        this.jobScheduleId = jobScheduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFreqType() {
        return freqType;
    }

    public void setFreqType(String freqType) {
        this.freqType = freqType;
    }
}

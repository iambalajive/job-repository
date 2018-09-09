package com.balaji.jobs.core;

public class JobConfig {

    private String jobConfigId;
    private String jobId;
    private int freqMetaId;
    private int freqVal;

    public JobConfig(String jobConfigId, String jobId, int freqMetaId, int freqVal) {
        this.jobConfigId = jobConfigId;
        this.jobId = jobId;
        this.freqMetaId = freqMetaId;
        this.freqVal = freqVal;
    }


    public String getJobConfigId() {
        return jobConfigId;
    }

    public void setJobConfigId(String jobConfigId) {
        this.jobConfigId = jobConfigId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getFreqMetaId() {
        return freqMetaId;
    }

    public void setFreqMetaId(int freqMetaId) {
        this.freqMetaId = freqMetaId;
    }

    public int getFreqVal() {
        return freqVal;
    }

    public void setFreqVal(int freqVal) {
        this.freqVal = freqVal;
    }

}

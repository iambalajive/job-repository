package com.balaji.jobs.models;

public class JobReq {

    private String jobType ;

    private String freqType;

    private int freqVal;

    private String name;

    private String execDtls;


    public String getName() {
        return name;
    }

    public String getFreqType() {
        return freqType;
    }

    public int getFreqVal() {
        return freqVal;
    }

    public String getExecDtls() {
        return execDtls;
    }

    public String getJobType() {
        return jobType;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setFreqType(String freqType) {
        this.freqType = freqType;
    }

    public void setFreqVal(int freqVal) {
        this.freqVal = freqVal;
    }

    public void setExecDtls(String execDtls) {
        this.execDtls = execDtls;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

}

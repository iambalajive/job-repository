package com.balaji.jobs.models;


public class JobResponse {

    private String name;
    private String execDtls;
    private String id;

    public String getExecDtls() {
        return execDtls;
    }

    public String getId() {
        return id;
    }

    public JobResponse(String name, String execDtls, String id) {
        this.name = name;
        this.execDtls = execDtls;
        this.id = id;

    }

    public JobResponse(String name, String execDtls) {
        this.name = name;
        this.execDtls = execDtls;
    }

    public String getName() {
        return name;
    }
}

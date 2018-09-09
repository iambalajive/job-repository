package com.balaji.jobs.core;

import java.util.UUID;

public class Jobs {

    private String id;
    private String name;
    private String executionDetails;

    public String getId() {
        return id;
    }


    public Jobs(String id, String name, String executionDetails) {
        this.id = id;
        this.name = name;
        this.executionDetails = executionDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutionDetails() {
        return executionDetails;
    }

    public void setExecutionDetails(String executionDetails) {
        this.executionDetails = executionDetails;
    }
}

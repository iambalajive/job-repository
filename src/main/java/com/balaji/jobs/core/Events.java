package com.balaji.jobs.core;

public class Events {

    private String eventId;

    private String name;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;

    }

    private String jobId;

    public Events(String eventId, String name) {
        this.eventId = eventId;
        this.name = name;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

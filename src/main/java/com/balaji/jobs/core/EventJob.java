package com.balaji.jobs.core;

public class EventJob {

    private String eventId;
    private String jobId;

    public EventJob(String eventId, String jobId) {
        this.eventId = eventId;
        this.jobId = jobId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}

package com.balaji.jobs.models;

public class EventReq {
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public String getAssocJobId() {
        return assocJobId;
    }

    public void setAssocJobId(String assocJobId) {
        this.assocJobId = assocJobId;
    }

    private String eventName;
    private String assocJobId;


}

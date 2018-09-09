package com.balaji.jobs.resources;


import com.balaji.jobs.core.EventJob;
import com.balaji.jobs.dao.EventDAO;
import com.balaji.jobs.dao.JobScheduleDAO;
import com.balaji.jobs.models.AdhocJobTriggerReq;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("/job-execution")
@Produces(MediaType.APPLICATION_JSON)
public class TriggerJobResource {

    private EventDAO eventDAO;
    private JobScheduleDAO jobScheduleDAO;


    public TriggerJobResource(EventDAO eventDAO, JobScheduleDAO jobScheduleDAO) {
        this.eventDAO = eventDAO;
        this.jobScheduleDAO = jobScheduleDAO;
    }

    //Post api that triggers the job associated with the event
    @POST
    @Timed
    public Response triggerByEvent(AdhocJobTriggerReq adhocJobTriggerReq) {
        List<EventJob> events = eventDAO.findEventByName(adhocJobTriggerReq.getEventName());

        //If events are not preset - invalid event name
        if (events.isEmpty()) {
            return Response.status(400).entity(new HashMap<String, String>() {{
                put("msg", "Event name not found");
            }}).build();
        } else {
            //On Valid event , schedule all jobs tagged to the event for immediate execution
            for(EventJob eventJob: events) {
                jobScheduleDAO.createJobSchedule(eventJob.getJobId(), System.currentTimeMillis());
            }

            return Response.status(200).entity(new HashMap<String, String>() {{
                put("msg", "Successfully scheduled the job ");
            }}).build();
        }

    }
}

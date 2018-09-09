package com.balaji.jobs.resources;


import com.balaji.jobs.core.Events;
import com.balaji.jobs.dao.EventDAO;
import com.balaji.jobs.models.EventReq;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/evnt")
@Produces(MediaType.APPLICATION_JSON)
public class EvntResource {

    private EventDAO eventDAO;

    public EvntResource(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @PUT
    @Timed
    public Events createEvent(EventReq eventReq) {
        return eventDAO.createEvent(eventReq.getEventName(), eventReq.getAssocJobId());

    }

    @GET
    public List<Events> listEvnts() {
        return eventDAO.fetchAllEvents();
    }


    @GET
    @Path("/{id}")
    public List<Events> getJob(@PathParam("id") String id) {
        return eventDAO.findEventById(id);
    }


}
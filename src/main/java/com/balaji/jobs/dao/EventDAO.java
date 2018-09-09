package com.balaji.jobs.dao;

import com.balaji.jobs.core.EventJob;
import com.balaji.jobs.core.Events;
import com.balaji.jobs.dao.mapper.EventJobMapper;
import com.balaji.jobs.dao.mapper.EventResultMapper;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.UUID;

public class EventDAO {

    private DBI dbi;

    public EventDAO(DBI dbi) {
        this.dbi = dbi;
    }

    public List<Events> fetchAllEvents() {
        Handle handle = this.dbi.open();
        try {
            return handle.createQuery("select * from EVNT").map(new EventResultMapper()).list();
        } finally {
            handle.close();
        }

    }

    public List<EventJob> findEventByName(String name) {
        Handle handle = this.dbi.open();

        try {
            String query = "select evj.evnt_id,evj.job_id from evnt_job evj join evnt ev on " +
                    "evj.evnt_id = ev.evnt_id where ev.name = " + "'" + name + "'";

            return handle.createQuery(query).map(new EventJobMapper()).list();
        } finally {
            handle.close();
        }

    }

    public List<Events> findEventById(String id) {
        Handle handle = this.dbi.open();

        try {
            String query = "select * from EVNT where EVNT_ID = " + "'" + id + "'";

            return handle.createQuery(query).map(new EventResultMapper()).list();
        } finally {
            handle.close();
        }

    }


    public Events createEvent(String evntName, String asscJobId) {
        Handle handle = this.dbi.open();

        String evntId = UUID.randomUUID().toString();
        String evntJobId = UUID.randomUUID().toString();
        try {
            handle.insert("Insert into EVNT (EVNT_ID,NAME) values(?,?)", evntId, evntName);

            handle.insert("Insert into EVNT_JOB (EVNT_JOB_ID,EVNT_ID,JOB_ID) values(?,?,?)", evntJobId, evntId, asscJobId);

            Events events = new Events(evntId, evntName);
            events.setJobId(evntJobId);
            return events;
        } finally {
            handle.close();
        }

    }

}

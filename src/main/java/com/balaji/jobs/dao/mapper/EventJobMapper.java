package com.balaji.jobs.dao.mapper;

import com.balaji.jobs.core.EventJob;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EventJobMapper implements ResultSetMapper<EventJob> {
    @Override
    public EventJob map(int arg0, ResultSet arg1, StatementContext arg2) throws SQLException {
        return new EventJob(arg1.getString("EVNT_ID"), arg1.getString("JOB_ID"));
    }

}
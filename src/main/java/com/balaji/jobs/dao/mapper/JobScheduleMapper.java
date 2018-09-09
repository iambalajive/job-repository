package com.balaji.jobs.dao.mapper;

import com.balaji.jobs.core.JobSchedule;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobScheduleMapper implements ResultSetMapper<JobSchedule> {
    @Override
    public JobSchedule map(int arg0, ResultSet arg1, StatementContext arg2) throws SQLException {
        return new JobSchedule(arg1.getString("JOB_SCHEDULE_ID"),
                arg1.getLong("NEXT_RUN_TIME"),
                arg1.getString("JOB_ID"));
    }

}
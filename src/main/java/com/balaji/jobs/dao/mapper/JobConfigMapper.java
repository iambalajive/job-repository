package com.balaji.jobs.dao.mapper;

import com.balaji.jobs.core.JobConfig;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class JobConfigMapper implements ResultSetMapper<JobConfig> {
    @Override
    public JobConfig map(int arg0, ResultSet arg1, StatementContext arg2) throws SQLException {
        return new JobConfig(arg1.getString("JOB_CONFIG_ID"), arg1.getString("JOB_ID"),
                arg1.getInt("FREQ_META_ID"), arg1.getInt("FREQ_VAL"));
    }

}

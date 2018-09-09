package com.balaji.jobs.dao.mapper;

import com.balaji.jobs.core.Jobs;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobResultSetMapper implements ResultSetMapper<Jobs> {
    @Override
    public Jobs map(int arg0, ResultSet arg1, StatementContext arg2) throws SQLException {
        return new Jobs(arg1.getString("JOB_ID"), arg1.getString("NAME"), arg1.getString("EXEC_DTLS"));
    }

}

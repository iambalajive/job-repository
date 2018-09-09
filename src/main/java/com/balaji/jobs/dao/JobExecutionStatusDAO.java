package com.balaji.jobs.dao;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.UUID;

public class JobExecutionStatusDAO {
    private DBI dbi;

    public JobExecutionStatusDAO(DBI dbi) {
        this.dbi = dbi;
    }

    public String createJobExecutionStatus(String jobId, int status, long startTime, long endTime) {
        Handle handle = this.dbi.open();

        String id = UUID.randomUUID().toString();
        try {
            handle.insert("Insert into JOB_EXECUTION_STATUS (JOB_EXECUTION_STATUS_ID,JOB_ID,START_TIME,END_TIME,JOB_STATUS_META_ID) values(?,?,?,?,?)",
                    id, jobId, startTime, endTime, status);

            return id;
        } finally {
            handle.close();
        }

    }

}

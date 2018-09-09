package com.balaji.jobs.dao;

import com.balaji.jobs.core.Jobs;
import com.balaji.jobs.dao.mapper.JobResultSetMapper;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.UUID;

public class JobDAO {

    private DBI dbi;

    public JobDAO(DBI dbi) {
        this.dbi = dbi;
    }

    public List<Jobs> fetchAllJobs() {
        Handle handle = this.dbi.open();
        try {
            return handle.createQuery("select * from jobs").map(new JobResultSetMapper()).list();
        } finally {
            handle.close();
        }

    }

    public List<Jobs> findJobById(String id) {
        Handle handle = this.dbi.open();

        try {
            String query = "select * from jobs where JOB_ID = " + "'" + id + "'";

            System.out.println(query);

            return handle.createQuery(query).map(new JobResultSetMapper()).list();
        } finally {
            handle.close();
        }

    }


    public Jobs createJob(String jobName, String execDtls, int jobTypeId) {
        Handle handle = this.dbi.open();

        String id = UUID.randomUUID().toString();
        try {
            handle.insert("Insert into jobs (JOB_ID,NAME,EXEC_DTLS,job_type_id) values(?,?,?,?)", id, jobName, execDtls, jobTypeId);
            return new Jobs(id, jobName, execDtls);
        } finally {
            handle.close();
        }

    }

}

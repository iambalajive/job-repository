
package com.balaji.jobs.dao;

import com.balaji.jobs.core.JobConfig;
import com.balaji.jobs.dao.mapper.JobConfigMapper;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.UUID;

public class JobConfigDAO {

    private DBI dbi;

    public JobConfigDAO(DBI dbi) {
        this.dbi = dbi;
    }

    public List<JobConfig> fetchJobNotScheduled() {
        Handle handle = this.dbi.open();
        try{
            String query ="select jc.* from jobs jb join job_config jc\n" +
                    "on jb.job_id = jc.job_id where job_type_id=1 and not exists (select 1 from job_schedule js where js.job_id = jb.job_id )";

            return handle.createQuery(query).map(new JobConfigMapper()).list();
        }finally {
            handle.close();
        }
    }
    public List<JobConfig> fetchAllJobConfigs() {
        Handle handle = this.dbi.open();
        try {
            return handle.createQuery("select * from JOB_CONFIG").map(new JobConfigMapper()).list();
        } finally {
            handle.close();
        }

    }




    public JobConfig createJobConfig(String jobId, int freqMetaId, int freqVal) {
        Handle handle = this.dbi.open();

        String id = UUID.randomUUID().toString();
        try {
            handle.insert("Insert into JOB_CONFIG (JOB_CONFIG_ID,JOB_ID,FREQ_META_ID,FREQ_VAL) values(?,?,?,?)", id, jobId, freqMetaId, freqVal);

            return new JobConfig(id, jobId, freqMetaId, freqVal);
        } finally {
            handle.close();
        }

    }
}

package com.balaji.jobs.dao;

import com.balaji.jobs.core.JobSchedule;
import com.balaji.jobs.core.Task;
import com.balaji.jobs.dao.mapper.JobScheduleMapper;
import com.balaji.jobs.dao.mapper.TaskMapper;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.UUID;

public class JobScheduleDAO {

    private DBI dbi;

    public JobScheduleDAO(DBI dbi) {
        this.dbi = dbi;
    }

    public List<JobSchedule> findJobScheduleById(String id) {
        Handle handle = this.dbi.open();

        String query = "select * from job_schedule where job_schedule_id =" + "'" + id + "'";
        try {
            return handle.createQuery(query).map(new JobScheduleMapper()).list();
        }finally {
            handle.close();
        }

    }

    public List<Task> findScheduleLessThanTimestamp(long timeInMillis) {
        Handle handle = this.dbi.open();

        try {
            String query = "select jb.job_id,js.job_schedule_id,jb.name,jb.exec_dtls,freq_val,fm.freq_type from job_schedule js " +
                    "join jobs jb on jb.job_id = js.job_id left join job_config jc on jc.job_id = jb.job_id " +
                    " left join freq_meta fm on fm.freq_meta_id = jc.freq_meta_id where js.next_run_time <= " + timeInMillis +" order by next_run_time asc";


            return handle.createQuery(query).map(new TaskMapper()).list();
        } finally {
            handle.close();
        }

    }

    public void deleteJobSchedule(String jobScheduleId) {
        Handle handle = this.dbi.open();

        try {
            handle.execute("delete from job_schedule where job_schedule_id = ? ",jobScheduleId);
        }finally {
            handle.close();
        }

    }


    public JobSchedule createJobSchedule(String jobId, Long nextRunTime) {
        Handle handle = this.dbi.open();

        String jobScheduleId = UUID.randomUUID().toString();
        try {
            handle.insert("Insert into JOB_SCHEDULE (JOB_SCHEDULE_ID,JOB_ID,NEXT_RUN_TIME) values(?,?,?)", jobScheduleId, jobId, nextRunTime);

            return new JobSchedule(jobScheduleId, nextRunTime, jobId);
        } finally {
            handle.close();
        }

    }
}

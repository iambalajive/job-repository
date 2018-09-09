package com.balaji.jobs.resources;


import com.balaji.jobs.core.Jobs;
import com.balaji.jobs.dao.EventDAO;
import com.balaji.jobs.dao.JobConfigDAO;
import com.balaji.jobs.dao.JobDAO;
import com.balaji.jobs.dao.JobScheduleDAO;
import com.balaji.jobs.models.JobReq;
import com.balaji.jobs.models.JobResponse;
import com.balaji.jobs.utils.Metadata;
import com.balaji.jobs.utils.TimeUtil;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("/jobs")
@Produces(MediaType.APPLICATION_JSON)
public class JobResource {

    private JobDAO jobDAO;

    private EventDAO eventDAO;

    private JobScheduleDAO jobScheduleDAO;

    private JobConfigDAO jobConfigDAO;

    public JobResource(JobDAO jobDAO, EventDAO eventDAO, JobScheduleDAO jobScheduleDAO, JobConfigDAO jobConfigDAO) {
        this.jobDAO = jobDAO;
        this.eventDAO = eventDAO;
        this.jobScheduleDAO = jobScheduleDAO;
        this.jobConfigDAO = jobConfigDAO;
    }

    @PUT
    @Timed
    public Response createJob(JobReq jobReq) {


        Integer jobTypeId = Metadata.JOBTYPES.get(jobReq.getJobType());

        //If it is not a valid job Type throw bad request

        if (jobTypeId == null) {
            return Response.status(400).entity(new HashMap<String, String>() {{
                put("msg", "Job type not supported");
            }}).build();
        }


        Jobs job = jobDAO.createJob(jobReq.getName(), jobReq.getExecDtls(), jobTypeId);

        //If it is a scheduled job add entry to jobSchedule table - to capture the next job run
        if (jobReq.getJobType().equals(Metadata.SCHEDULED)) {
            long nextTime = TimeUtil.getNextExecutionTimeinMillis(jobReq.getFreqType(), jobReq.getFreqVal());

            //If there is no freq for scheduled job throw error
            if (nextTime == -1) {
                return Response.status(400).entity(new HashMap<String, String>() {{
                    put("msg", "Frequency type not supported");
                }}).build();
            }

            jobScheduleDAO.createJobSchedule(job.getId(), nextTime);

            jobConfigDAO.createJobConfig(job.getId(), Metadata.FREQS.get(jobReq.getFreqType()), jobReq.getFreqVal());

        }


        //Success Case
        JobResponse jobResponse = new JobResponse(job.getName(), job.getExecutionDetails(), job.getId());
        return Response.status(200).entity(jobResponse).build();

    }

    @GET
    public List<Jobs> listJobs(@PathParam("id") String id) {
        return jobDAO.fetchAllJobs();
    }


    @GET
    @Path("/{id}")
    public List<Jobs> getJob(@PathParam("id") String id) {
        return jobDAO.findJobById(id);
    }


}

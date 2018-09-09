package com.balaji.jobs.scheduler;

import com.balaji.jobs.core.Task;
import com.balaji.jobs.core.TaskMeta;
import com.balaji.jobs.utils.Metadata;


// Task executor thread that is used by The thread pool
//to actually perform the task
public class TaskExecutor implements Runnable {

    private Task task;
    private ConcurrentStatusUpdater concurrentStatusUpdater;


    public TaskExecutor(Task task, ConcurrentStatusUpdater concurrentStatusUpdater) {
        this.task = task;
        this.concurrentStatusUpdater = concurrentStatusUpdater;
    }



    public void run() {

        TaskMeta taskMeta = task.getTaskMeta();
        long jobStartTime = System.currentTimeMillis();
        try {

            if(!concurrentStatusUpdater.isJobExecutionAlreadyComplete(taskMeta.getJobScheduleId())){

                //This is executing the Task . This will do a print
                //Can be made complicated to execute as async
                //For not the task with just print the content

                System.out.println("Executing task " + task.getTaskDtls() + " for job id " +taskMeta.getJobId());

                long endTime = System.currentTimeMillis();

                int successStatusCode = Metadata.JOBSTATUS.get(Metadata.SUCCESS);

                concurrentStatusUpdater.updateStatus(task,successStatusCode, jobStartTime,endTime);
            }



        } catch (Exception ex) {

            //For debug purpose
            //In case of exception mark the job as failed
            int failureStatusCode = Metadata.JOBSTATUS.get(Metadata.FAILED);
            System.out.println(ex);
            //Mark the status of current job run as failed
            concurrentStatusUpdater.updateStatus(task,failureStatusCode,jobStartTime,System.currentTimeMillis());
        }


    }
}

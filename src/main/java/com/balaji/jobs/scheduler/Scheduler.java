package com.balaji.jobs.scheduler;

import com.balaji.jobs.core.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {


    // Executor service that holds the thread pool
    private ExecutorService executorService;
    private ConcurrentStatusUpdater concurrentStatusUpdater;

    public Scheduler(int executorCnt,ConcurrentStatusUpdater concurrentStatusUpdater) {
        this.executorService = Executors.newFixedThreadPool(executorCnt);
        this.concurrentStatusUpdater = concurrentStatusUpdater;
    }


    //These tasks are already sorted and prioritized
    // as we pick them by asc order of time

    public void schedule(List<Task> items) {
        for(Task task: items) {
            executorService.submit(new TaskExecutor(task,this.concurrentStatusUpdater));
        }
    }


}

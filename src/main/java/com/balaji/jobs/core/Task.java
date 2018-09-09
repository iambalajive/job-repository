package com.balaji.jobs.core;

public class Task {
    private String taskDtls;

    public Task(String taskDtls, TaskMeta taskMeta) {
        this.taskDtls = taskDtls;
        this.taskMeta = taskMeta;
    }

    public String getTaskDtls() {
        return taskDtls;

    }

    public void setTaskDtls(String taskDtls) {
        this.taskDtls = taskDtls;
    }

    public TaskMeta getTaskMeta() {
        return taskMeta;
    }

    public void setTaskMeta(TaskMeta taskMeta) {
        this.taskMeta = taskMeta;
    }

    private TaskMeta taskMeta;

}

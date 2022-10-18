package com.src.projectmanagementtoolservice.exceptions.responses;

public class TaskIDExceptionResponse {


    private Long taskID;

    TaskIDExceptionResponse(Long taskID) {
        this.taskID = taskID;
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }


}

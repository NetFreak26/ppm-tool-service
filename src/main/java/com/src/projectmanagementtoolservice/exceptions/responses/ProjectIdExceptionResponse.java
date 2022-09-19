package com.src.projectmanagementtoolservice.exceptions.responses;

public class ProjectIdExceptionResponse {

    private String projectID;

    public ProjectIdExceptionResponse(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
}

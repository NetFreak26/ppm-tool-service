package com.src.projectmanagementtoolservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Project ID is required")
    @Size(min = 5, max = 6, message = "Project ID should be between 5 to 6 length")
    @Column(updatable = false, unique = true)
    private String projectId;

    @NotBlank(message = "Project description can not be blank")
    private String projectDescription;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    private String updatedBy;

    public Project() {

    }

    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @PrePersist
    public void onCreate() {
        createdAt = new Date();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = new Date();
    }


}

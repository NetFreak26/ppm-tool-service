package com.src.projectmanagementtoolservice.services;

import com.src.projectmanagementtoolservice.domain.Backlog;
import com.src.projectmanagementtoolservice.domain.ProjectTask;
import com.src.projectmanagementtoolservice.exceptions.ProjectNotFoundException;
import com.src.projectmanagementtoolservice.repositories.BacklogRepository;
import com.src.projectmanagementtoolservice.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BacklogService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskService projectTaskService;

    public ProjectTask addProjectTask(ProjectTask projectTask, String projectID) {
        Backlog backlog = getBacklog(projectID);
        backlog.addProjectTask(projectTask);
        return projectTaskService.saveProjectTask(projectTask, projectID, backlog);
    }

    public Iterable<ProjectTask> getAllProjectTasks(String projectID) {
        Backlog backlog = getBacklog(projectID);
        return backlog.getProjectTasks();
    }

    private Backlog getBacklog(String projectID) {
        Backlog backlog = backlogRepository.findByProjectID(projectID);
        if(backlog == null) {
            throw new ProjectNotFoundException("Project not found with projectId " + projectID);
        }
        return backlog;
    }


}

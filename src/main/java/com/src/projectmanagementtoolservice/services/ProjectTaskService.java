package com.src.projectmanagementtoolservice.services;

import com.src.projectmanagementtoolservice.domain.Backlog;
import com.src.projectmanagementtoolservice.domain.Project;
import com.src.projectmanagementtoolservice.domain.ProjectTask;
import com.src.projectmanagementtoolservice.exceptions.ProjectNotFoundException;
import com.src.projectmanagementtoolservice.exceptions.TaskNotFoundException;
import com.src.projectmanagementtoolservice.repositories.BacklogRepository;
import com.src.projectmanagementtoolservice.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public ProjectTask saveProjectTask(ProjectTask projectTask, String projectID, Backlog backlog) {

        projectTask.setBacklog(backlog);

        Integer PTSequence = backlog.getPTSequence();
        PTSequence++;
        backlog.setPTSequence(PTSequence);
        projectTask.setTaskID(projectID.toUpperCase() + "-" + PTSequence);

        projectTask.setProjectID(projectID.toUpperCase());

        if(StringUtils.isEmpty(projectTask.getStatus())) {
            projectTask.setStatus(("TO_DO"));
        }

        if(StringUtils.isEmpty(projectTask.getPriority())) {
            projectTask.setStatus(("LOW"));
        }
        return projectTaskRepository.save(projectTask);
    }

    public ProjectTask getProjectTaskByTaskID(String taskID) {
        ProjectTask projectTask = projectTaskRepository.findByTaskID(taskID);
        if(projectTask != null) {
            return projectTask;
        } else {
            throw new TaskNotFoundException("No Task found with task ID " + taskID);
        }
    }

    public ProjectTask updateProjectTask(ProjectTask updatedProjectTask) {
        String taskID = updatedProjectTask.getTaskID();
        ProjectTask projectTask = projectTaskRepository.findByTaskID(taskID);
        if(projectTask != null) {
            return projectTaskRepository.save(updatedProjectTask);
        } else {
            throw new TaskNotFoundException("No Task found with task ID " + taskID);
        }
    }

    public ProjectTask deleteProjectTask(String taskID) {
        ProjectTask projectTask = projectTaskRepository.findByTaskID(taskID);
        if(projectTask != null) {
            projectTaskRepository.delete(projectTask);
            return projectTask;
        } else {
            throw new TaskNotFoundException("No Task found with task ID " + taskID);
        }
    }
}

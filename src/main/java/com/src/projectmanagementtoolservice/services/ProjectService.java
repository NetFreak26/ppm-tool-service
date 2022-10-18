package com.src.projectmanagementtoolservice.services;

import com.src.projectmanagementtoolservice.domain.Backlog;
import com.src.projectmanagementtoolservice.domain.Project;
import com.src.projectmanagementtoolservice.exceptions.IdDuplicateException;
import com.src.projectmanagementtoolservice.exceptions.ProjectNotFoundException;
import com.src.projectmanagementtoolservice.repositories.BacklogRepository;
import com.src.projectmanagementtoolservice.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveProject(Project project) {
        String temp = project.getProjectID();
        try {
            project.setProjectID(project.getProjectID().toUpperCase());
            if(project.getId() == null) {
                Backlog backlog = new Backlog();
                backlog.setProjectID(project.getProjectID());
                backlog.setProject(project);
                project.setBacklog(backlog);
            } else {
                project.setBacklog(backlogRepository.findByProjectID(project.getProjectID()));
            }

            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new IdDuplicateException("Project Id " + temp + " already exists.");
        }

    }

    public Project findProjectByID(String projectID) {
        if(projectID == null || projectID.isEmpty()) {
            throw new ProjectNotFoundException("ProjectId can not be null or empty.");
        }
        return getProjectFromRepo(projectID.toUpperCase());
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByID(String projectID) {
        Project project = getProjectFromRepo(projectID.toUpperCase());
        projectRepository.delete(project);
    }

    private Project getProjectFromRepo(String projectID) {
        Project project = projectRepository.findByProjectID(projectID.toUpperCase());

        if(project != null) {
            return project;
        } else {
            throw new ProjectNotFoundException("Project not found with projectId " + projectID);
        }
    }
}

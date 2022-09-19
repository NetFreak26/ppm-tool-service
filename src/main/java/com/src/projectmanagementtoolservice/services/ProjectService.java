package com.src.projectmanagementtoolservice.services;

import com.src.projectmanagementtoolservice.domain.Project;
import com.src.projectmanagementtoolservice.exceptions.IdDuplicateException;
import com.src.projectmanagementtoolservice.exceptions.ProjectNotFoundException;
import com.src.projectmanagementtoolservice.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        try {
            project.setProjectId(project.getProjectId().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new IdDuplicateException("Project Id " + project.getProjectId() + " already exists.");
        }

    }

    public Project findProjectById(String projectId) {
        if(projectId == null || projectId.isEmpty()) {
            throw new ProjectNotFoundException("ProjectId can not be null or empty.");
        }
        return getProjectFromRepo(projectId.toUpperCase());
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectById(String projectId) {
        Project project = getProjectFromRepo(projectId.toUpperCase());
        projectRepository.delete(project);
    }

    private Project getProjectFromRepo(String projectId) {
        Project project = projectRepository.findByProjectId(projectId.toUpperCase());

        if(project != null) {
            return project;
        } else {
            throw new ProjectNotFoundException("Project not found with projectId " + projectId);
        }
    }
}

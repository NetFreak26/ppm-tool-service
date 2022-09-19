package com.src.projectmanagementtoolservice.controllers;

import com.src.projectmanagementtoolservice.domain.Project;
import com.src.projectmanagementtoolservice.services.ProjectService;
import com.src.projectmanagementtoolservice.services.ValidateErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    ValidateErrorService validateErrorService;

    @PostMapping("/save")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = validateErrorService.mapValidationError(result);
        if(errorMap != null) {
            return errorMap;
        }
        Project savedProject = projectService.saveProject(project);
        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findProjectById(@RequestParam String projectId) {
        Project project = projectService.findProjectById(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public Iterable<Project> findAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProjectById(@RequestParam String projectId) {
        projectService.deleteProjectById(projectId);
        return new ResponseEntity<String>("Project deleted Successfully with Id " + projectId, HttpStatus.OK);
    }
}

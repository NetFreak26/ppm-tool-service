package com.src.projectmanagementtoolservice.controllers;

import com.src.projectmanagementtoolservice.domain.Project;
import com.src.projectmanagementtoolservice.services.ProjectService;
import com.src.projectmanagementtoolservice.services.ValidateErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ValidateErrorService validateErrorService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = validateErrorService.mapValidationError(result);
        if(errorMap != null) {
            return errorMap;
        }
        Project savedProject = projectService.saveProject(project);
        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findProjectByID(@RequestParam String projectID) {
        Project project = projectService.findProjectByID(projectID);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public Iterable<Project> findAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProjectByID(@RequestParam String projectID) {
        System.out.println();
        projectService.deleteProjectByID(projectID);
        return new ResponseEntity<String>("Project deleted Successfully with ID " + projectID, HttpStatus.OK);
    }
}

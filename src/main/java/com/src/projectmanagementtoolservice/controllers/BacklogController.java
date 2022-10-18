package com.src.projectmanagementtoolservice.controllers;

import com.src.projectmanagementtoolservice.domain.ProjectTask;
import com.src.projectmanagementtoolservice.services.BacklogService;
import com.src.projectmanagementtoolservice.services.ValidateErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
public class BacklogController {

    @Autowired
    BacklogService backlogService;

    @Autowired
    ValidateErrorService validateErrorService;

    @PostMapping("/addTask")
    public ResponseEntity<?> addProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @RequestParam String projectID) {
        ResponseEntity<?> errorMap = validateErrorService.mapValidationError(result);
        if(errorMap != null) {
            return errorMap;
        }

        ProjectTask savedProjectTask = backlogService.addProjectTask(projectTask, projectID);
        return new ResponseEntity<>(savedProjectTask, HttpStatus.CREATED);
    }

    @GetMapping("/findAllTasks")
    public Iterable<ProjectTask> findAllProjectTasks(@RequestParam String projectID) {
        return backlogService.getAllProjectTasks(projectID);
    }
}

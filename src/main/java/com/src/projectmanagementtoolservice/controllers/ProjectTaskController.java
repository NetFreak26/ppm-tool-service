package com.src.projectmanagementtoolservice.controllers;

import com.src.projectmanagementtoolservice.domain.ProjectTask;
import com.src.projectmanagementtoolservice.services.ProjectTaskService;
import com.src.projectmanagementtoolservice.services.ValidateErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private ValidateErrorService validateErrorService;

    @GetMapping("/findTask")
    public ResponseEntity<?> findProjectTask(@RequestParam String taskID) {
        ProjectTask projectTask = projectTaskService.getProjectTaskByTaskID(taskID);
        return new ResponseEntity<>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/updateTask")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result) {
        ResponseEntity<?> errorMap = validateErrorService.mapValidationError(result);
        if(errorMap != null) {
            return errorMap;
        }

        ProjectTask savedProjectTask = projectTaskService.updateProjectTask(projectTask);
        return new ResponseEntity<>(savedProjectTask, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<?> deleteProjectTask(@RequestParam String taskID) {
        projectTaskService.deleteProjectTask(taskID);
        return new ResponseEntity<>("Task Deleted Successfully", HttpStatus.OK);
    }
}

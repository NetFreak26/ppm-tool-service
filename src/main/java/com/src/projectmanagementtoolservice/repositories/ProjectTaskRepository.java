package com.src.projectmanagementtoolservice.repositories;

import com.src.projectmanagementtoolservice.domain.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {

    ProjectTask findByTaskID(String taskID);

}

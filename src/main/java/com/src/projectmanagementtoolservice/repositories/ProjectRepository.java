package com.src.projectmanagementtoolservice.repositories;

import com.src.projectmanagementtoolservice.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public Project findByProjectID(String projectId);
}

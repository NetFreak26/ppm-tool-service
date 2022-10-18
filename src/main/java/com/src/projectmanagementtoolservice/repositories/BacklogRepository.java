package com.src.projectmanagementtoolservice.repositories;

import com.src.projectmanagementtoolservice.domain.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    Backlog findByProjectID(String projectID);
}

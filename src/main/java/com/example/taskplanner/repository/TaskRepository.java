package com.example.taskplanner.repository;

import com.example.taskplanner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignee(String assignee);
    List<Task> findBySprintId(Long sprintId);
}

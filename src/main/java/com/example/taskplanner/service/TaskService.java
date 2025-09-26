package com.example.taskplanner.service;

import com.example.taskplanner.model.Sprint;
import com.example.taskplanner.model.Task;
import com.example.taskplanner.repository.SprintRepository;
import com.example.taskplanner.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;

    public TaskService(TaskRepository taskRepository, SprintRepository sprintRepository) {
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
    }

    // Create a task without a sprint
    public Task createTask(Task task) {
        task.setCreatedDate(LocalDate.now());
        task.setStatus(task.getStatus() == null ? "TODO" : task.getStatus());
        return taskRepository.save(task);
    }

    // Create a task under a specific sprint
    public Task createTaskWithSprint(Long sprintId, Task task) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found with id: " + sprintId));
        task.setSprint(sprint);
        task.setCreatedDate(LocalDate.now());
        task.setStatus(task.getStatus() == null ? "TODO" : task.getStatus());
        return taskRepository.save(task);
    }

    // Update a task
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        if (updatedTask.getTitle() != null) existingTask.setTitle(updatedTask.getTitle());
        if (updatedTask.getDescription() != null) existingTask.setDescription(updatedTask.getDescription());
        if (updatedTask.getAssignee() != null) existingTask.setAssignee(updatedTask.getAssignee());
        if (updatedTask.getType() != null) existingTask.setType(updatedTask.getType());

        // Update status and set start/end dates
        String newStatus = updatedTask.getStatus();
        if (newStatus != null && !newStatus.equals(existingTask.getStatus())) {
            existingTask.setStatus(newStatus);

            if ("IN_PROGRESS".equals(newStatus)) {
                existingTask.setStartDate(LocalDate.now());
            } else if ("DONE".equals(newStatus)) {
                existingTask.setEndDate(LocalDate.now());
            }
        }

        return taskRepository.save(existingTask);
    }

    // Fetch tasks by assignee
    public List<Task> getTasksByAssignee(String assignee) {
        return taskRepository.findByAssignee(assignee);
    }

    // Fetch tasks by sprint ID
    public List<Task> getTasksBySprint(Long sprintId) {
        return taskRepository.findBySprintId(sprintId);
    }

    // Delete a task by ID
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}

package com.example.taskplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String type; // Bug, Feature, Story
    private String status; // TODO, IN_PROGRESS, DONE
    private String assignee;

    private LocalDate createdDate;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    @JsonBackReference
    private Sprint sprint;

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public String getAssignee() { return assignee; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public Sprint getSprint() { return sprint; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
    public void setStatus(String status) { this.status = status; }
    public void setAssignee(String assignee) { this.assignee = assignee; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setSprint(Sprint sprint) { this.sprint = sprint; }
}

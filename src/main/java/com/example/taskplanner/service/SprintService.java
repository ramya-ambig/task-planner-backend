package com.example.taskplanner.service;

import com.example.taskplanner.model.Sprint;
import com.example.taskplanner.repository.SprintRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    // Create a new sprint
    public Sprint createSprint(Sprint sprint) {
        if (sprint.getStartDate() == null) {
            sprint.setStartDate(LocalDate.now());
        }
        return sprintRepository.save(sprint);
    }

    // Get all sprints
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    // Delete a sprint by ID
    public void deleteSprint(Long id) {
        if (!sprintRepository.existsById(id)) {
            throw new RuntimeException("Sprint not found with id: " + id);
        }
        sprintRepository.deleteById(id);
    }
}

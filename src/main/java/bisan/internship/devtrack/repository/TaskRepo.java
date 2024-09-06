package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByProjectProjectId(long projectId);


    List<Task> findByAssignedToId(long userId);


    List<Task> findByBoardBoardId(long boardId);

    // New query method to find tasks by dueDate before a specific time
    List<Task> findByDueDateBefore(LocalDateTime dateTime);
}

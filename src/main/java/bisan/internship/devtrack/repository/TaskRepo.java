package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByProjectProjectId(long projectId);

    List<Task> findByAssignedToUserId(long userId);

    List<Task> findByBoardBoardId(long boardId);
}

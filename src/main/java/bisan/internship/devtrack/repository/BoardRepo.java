package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepo extends JpaRepository<Board, Long> {
    List<Board> findByProjectId(Project project);
}

package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepo extends JpaRepository<Board, Long> {
    List<Board> findByProjectProjectId(long projectId);
}

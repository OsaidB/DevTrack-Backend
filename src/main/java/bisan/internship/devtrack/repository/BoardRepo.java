package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends JpaRepository<Board, Long> {
}

package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board, Long> {
}

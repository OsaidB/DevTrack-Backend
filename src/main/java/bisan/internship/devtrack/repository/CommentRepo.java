package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}

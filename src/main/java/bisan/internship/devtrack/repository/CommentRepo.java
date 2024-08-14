package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByTaskTaskId(Long taskId);
}

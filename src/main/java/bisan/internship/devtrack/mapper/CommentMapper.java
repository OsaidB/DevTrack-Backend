package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.CommentDTO;
import bisan.internship.devtrack.model.entity.Comment;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;

public class CommentMapper {
    public static CommentDTO mapToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getCommentId(),
                comment.getTaskId().getTaskId(),
                comment.getCommentedBy().getUserId(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
    public static Comment mapToComment(CommentDTO commentDTO, User user, Task task) {
        return new Comment(
                commentDTO.getCommentId(),
                task,
                user,
                commentDTO.getComment(),
                commentDTO.getCreatedAt(),
                commentDTO.getUpdatedAt()
        );
    }
}

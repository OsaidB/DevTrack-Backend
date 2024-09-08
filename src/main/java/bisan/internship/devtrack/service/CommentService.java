package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO);
    CommentDTO getCommentById(Long commentId);
    List<CommentDTO> getAllComments();
    List<CommentDTO> getCommentsByTaskId(Long taskId);
    CommentDTO updateComment(Long commentId,CommentDTO commentDTO);
    void deleteComment(Long commentId);
    void deleteCommentsByTaskId(Long taskId);

}

package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.CommentDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.CommentMapper;
import bisan.internship.devtrack.model.entity.Comment;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.CommentRepo;
import bisan.internship.devtrack.repository.TaskRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        User user = userRepo.findById(commentDTO.getCommentedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found" + commentDTO.getCommentedBy()));
        Task task = taskRepo.findById(commentDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + commentDTO.getTaskId()));

        Comment comment = CommentMapper.mapToComment(commentDTO, user, task);
        Comment savedComment = commentRepo.save(comment);
        return CommentMapper.mapToCommentDTO(savedComment);
    }

    @Override
    public CommentDTO getCommentById(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found" + commentId));
        return CommentMapper.mapToCommentDTO(comment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepo.findAll();
        return comments.stream().map(CommentMapper::mapToCommentDTO).collect(Collectors.toList());
    }

    @Override
    public  List<CommentDTO> getCommentsByTaskId(Long taskId){
        taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + taskId));
        List<Comment> comments = commentRepo.findByTaskTaskId(taskId);
        return comments.stream().map(CommentMapper::mapToCommentDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(Long commentId ,CommentDTO updatedCommentDTO) {
        User user = userRepo.findById(updatedCommentDTO.getCommentedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found" + updatedCommentDTO.getCommentedBy()));
        Task task = taskRepo.findById(updatedCommentDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + updatedCommentDTO.getTaskId()));
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found" + updatedCommentDTO.getCommentId()));

        comment.setCommentedBy(user);
        comment.setUpdatedAt(updatedCommentDTO.getUpdatedAt());
        comment.setComment(updatedCommentDTO.getComment());
        comment.setTask(task);
//        comment.setCreatedAt(updatedCommentDTO.getCreatedAt());

        Comment savedComment = commentRepo.save(comment);
        return CommentMapper.mapToCommentDTO(savedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found" + commentId));
        commentRepo.delete(comment);
    }
}

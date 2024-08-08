package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.AttachmentDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.AttachmentMapper;
import bisan.internship.devtrack.model.entity.Attachment;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.AttachmentRepo;
import bisan.internship.devtrack.repository.TaskRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public AttachmentDTO createAttachment(AttachmentDTO attachmentDTO) {
        User user = userRepo.findById(attachmentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + attachmentDTO.getUserId()));
        Task task = taskRepo.findById(attachmentDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + attachmentDTO.getTaskId()));

        Attachment attachment = AttachmentMapper.mapToAttachmentEntity(attachmentDTO, user, task);
        Attachment attachmentSave = attachmentRepo.save(attachment);

        return AttachmentMapper.mapToAttachmentDto(attachmentSave);
    }

    @Override
    public AttachmentDTO getAttachmentById(Long attachmentId) {
        Attachment attachment = attachmentRepo.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with id: " + attachmentId));
        return AttachmentMapper.mapToAttachmentDto(attachment);
    }

    @Override
    public List<AttachmentDTO> getAllAttachments() {
        List<Attachment> attachments = attachmentRepo.findAll();
        return attachments.stream().map(AttachmentMapper::mapToAttachmentDto).collect(Collectors.toList());
    }

    @Override
    public AttachmentDTO updateAttachment(Long attachmentId, AttachmentDTO updateAttachmentDTO) {
        User user = userRepo.findById(updateAttachmentDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updateAttachmentDTO.getUserId()));
        Task task = taskRepo.findById(updateAttachmentDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + updateAttachmentDTO.getTaskId()));
        Attachment attachment = attachmentRepo.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with id: " + attachmentId));

        attachment.setUserId(user);
        attachment.setTaskId(task);
        attachment.setCreatedAt(updateAttachmentDTO.getCreatedAt());
        attachment.setFileURL(updateAttachmentDTO.getFileURL());
        attachment.setFileName(updateAttachmentDTO.getFileName());

        Attachment savedAttachment = attachmentRepo.save(attachment);
        return AttachmentMapper.mapToAttachmentDto(savedAttachment);
    }

    @Override
    public void deleteAttachment(Long attachmentId) {
        Attachment attachment = attachmentRepo.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with id: " + attachmentId));
        attachmentRepo.delete(attachment);
    }
}

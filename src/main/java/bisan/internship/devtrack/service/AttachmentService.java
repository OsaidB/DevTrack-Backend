package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.AttachmentDTO;
import java.util.List;

public interface AttachmentService {
    AttachmentDTO createAttachment(AttachmentDTO attachmentDTO);
    AttachmentDTO getAttachmentById(Long attachmentId);
    List<AttachmentDTO> getAllAttachments();
    List<AttachmentDTO> getAttachmentsByTaskId(Long taskId);
    AttachmentDTO updateAttachment(Long attachmentId , AttachmentDTO attachmentDTO);
    void deleteAttachment(Long attachmentId);
}

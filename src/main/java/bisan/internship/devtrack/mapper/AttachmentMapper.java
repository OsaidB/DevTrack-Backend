package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.AttachmentDTO;
import bisan.internship.devtrack.model.entity.Attachment;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;

public class AttachmentMapper {
    public static AttachmentDTO mapToAttachmentDto(Attachment attachment) {
        return new AttachmentDTO(
                attachment.getAttachmentId(),
                attachment.getTaskId().getTaskId(),
                attachment.getUserId().getUserId(),
                attachment.getFileName(),
                attachment.getFileURL(),
                attachment.getCreatedAt()
        );
    }
    public static Attachment mapToAttachmentEntity(AttachmentDTO attachmentDTO, User user, Task task) {
        return new Attachment(
                attachmentDTO.getAttachmentId(),
                task,
                user,
                attachmentDTO.getFileName(),
                attachmentDTO.getFileURL(),
                attachmentDTO.getCreatedAt()
        );
    }
}

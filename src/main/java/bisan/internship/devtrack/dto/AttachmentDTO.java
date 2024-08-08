package bisan.internship.devtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {
    private Long attachmentId;
    private Long taskId;
    private Long userId;
    private String fileName;
    private String fileURL;
    private LocalDateTime createdAt;
}

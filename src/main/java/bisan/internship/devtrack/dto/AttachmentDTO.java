package bisan.internship.devtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

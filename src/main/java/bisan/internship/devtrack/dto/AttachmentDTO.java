package bisan.internship.devtrack.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {
    private Long attachmentId;

    @NotNull(message = "Task ID cannot be null")
    private Long taskId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "File name cannot be null")
    @Size(max = 255, message = "File name must be less than 255 characters")
    private String fileName;

    @NotNull(message = "File URL cannot be null")
    @URL(message = "Invalid URL format")
    private String fileURL;

    private LocalDateTime createdAt;
}

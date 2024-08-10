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
public class CommentDTO {

    private Long commentId;
    private Long taskId;
    private Long commentedBy;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

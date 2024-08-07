package bisan.internship.devtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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

package bisan.internship.devtrack.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long commentId;

    @NotNull(message = "Task ID cannot be null")
    private Long taskId;

    @NotNull(message = "CommentedBy cannot be null")
    private Long commentedBy;

    @NotNull(message = "Comment cannot be null")
    @Size(max = 500, message = "Comment must be less than 500 characters")
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

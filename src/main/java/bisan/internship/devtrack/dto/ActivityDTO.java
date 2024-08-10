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
public class ActivityDTO {
    private Long activityId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Project ID cannot be null")
    private Long projectId;

    @NotNull(message = "Task ID cannot be null")
    private Long taskId;

    @NotNull(message = "Action cannot be null")
    @Size(max = 255, message = "Action must be less than 255 characters")
    private String action;

    private LocalDateTime createdAt;
}

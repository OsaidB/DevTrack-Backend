package bisan.internship.devtrack.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long taskId;
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
    @NotNull(message = "User ID cannot be null")
    private Long assignedToUserId;
    private String taskName;
    private String taskDescription;

    @NotNull(message = "Status cannot be null")
    @Pattern(regexp = "Pending|In Progress|Completed", message = "Invalid status")
    private String status;

    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

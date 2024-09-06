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

    //    @NotNull(message = "User ID cannot be null")
    private Long assignedToUserId;

    @NotNull(message = "Board ID cannot be null")
    private Long boardId;

    @NotNull(message = "Task name cannot be null")
    private String taskName;

    private String taskDescription;

    @NotNull(message = "Status cannot be null")
    @Pattern(regexp = "Unassigned Tasks|To Do|Doing|Ready to Review|Reviewing|Ready for QA|In Progress|QA Failed|QA Passed", message = "Invalid status")
    private String status;
    private String priority;

    private LocalDateTime dueDate;  // New field for task deadline

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

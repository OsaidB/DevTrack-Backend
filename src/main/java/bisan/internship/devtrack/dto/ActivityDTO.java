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
public class ActivityDTO {

    private Long activityId;
    private Long userId;
    private Long projectId;
    private Long taskId;
    private String action;
    private LocalDateTime createdAt;
}

package bisan.internship.devtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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

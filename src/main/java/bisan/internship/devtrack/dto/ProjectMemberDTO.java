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
public class ProjectMemberDTO {
    private Long projectMemberId;

    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
    @NotNull(message = "User ID cannot be null")
    private Long userId;
    private LocalDateTime joinedAt;
}

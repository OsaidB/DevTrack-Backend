package bisan.internship.devtrack.dto;

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
    private Long projectId;
    private Long userId;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

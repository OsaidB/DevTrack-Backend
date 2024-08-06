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
public class ProjectDTO {
    private Long projectId;
    private String name;
    private String description;
//    private Long createdBy; //always created by admin
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

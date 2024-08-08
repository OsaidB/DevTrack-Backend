package bisan.internship.devtrack.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "the name of the project cant be null")
    private String name;
    private String description;
//    private Long createdBy; //always created by admin
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

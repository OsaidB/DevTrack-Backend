package bisan.internship.devtrack.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, max = 100, message = "The name of the project must be between 1 and 100 characters")
    private String name;

//    @Size(max = 500, message = "The description must be less than 500 characters")
    private String description;
//    private Long createdBy; //always created by admin

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

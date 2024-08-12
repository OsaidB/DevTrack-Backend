package bisan.internship.devtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long boardId;

//    @NotNull(message = "Role cant be null")
//    private Long roleId;

    @Size(max = 100, message = "Board name must be less than 100 characters")
    private String name;

    @NotNull(message = "Project ID cannot be null")
    private Long projectId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

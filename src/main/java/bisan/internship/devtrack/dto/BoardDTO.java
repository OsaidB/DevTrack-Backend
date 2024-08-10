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
public class BoardDTO {
    private Long boardId;
    private String name;

    private Long projectId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

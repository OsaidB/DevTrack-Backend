package bisan.internship.devtrack.dto;

import bisan.internship.devtrack.model.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

package bisan.internship.devtrack.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project projectId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
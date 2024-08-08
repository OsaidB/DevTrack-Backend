package bisan.internship.devtrack.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task taskId;

    private String action;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

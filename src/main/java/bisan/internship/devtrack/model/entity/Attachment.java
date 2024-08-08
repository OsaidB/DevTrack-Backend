package bisan.internship.devtrack.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attachmentId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task taskId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    private String fileName;
    private String fileURL;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

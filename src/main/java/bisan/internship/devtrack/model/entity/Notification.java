package bisan.internship.devtrack.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false) // New field for sender
    private User sender; // Updated field name

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User recipient; // Updated field name

    private String message;
    private Boolean isRead;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

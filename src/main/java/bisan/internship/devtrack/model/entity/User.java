package bisan.internship.devtrack.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity //to make this class as a JPA Entity
@Table(name="main_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "email_id" ,nullable = false, unique = true)
    private String email;

    @Column(name = "pass", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private FunctionalRole  funcRole;//BackEnd?FrontEnd?QA?...

    @Column(name = "is_team_leader", nullable = false)
    private Boolean isTeamLeader;//This: ex(account is a BackEnd TeamLeader)

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;//time of created this account

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;//any edit on account info
}
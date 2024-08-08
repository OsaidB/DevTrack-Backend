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
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long userId;//Id

    private String username;//userName for u

    @Column(name = "email_id" ,nullable = false, unique = true)
    private String email;//Email

    @Column(name = "pass", nullable = false)
    private String password;//password

    @Column(name = "first_name", nullable = false)
    private String firstName;//FirstName

    @Column(name = "last_name", nullable = false)
    private String lastName;//LastName

    @Column(nullable = false)
    private String role;//BackEnd?FrontEnd?QA?...

    @Column(name = "is_team_leader", nullable = false)
    private Boolean isTeamLeader;//This: ex(account is a BackEnd TeamLeader)

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;//time of created this account

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;//any edit on account info
}
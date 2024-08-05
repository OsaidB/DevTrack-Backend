package bisan.internship.devtrack.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

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

    @Column(name = "email_id" ,nullable = true, unique = true)
    private String email;//Email

    @Column(name = "pass")
    private String password;//password

    @Column(name = "first_name")
    private String firstName;//FirstName

    @Column(name = "last_name")
    private String lastName;//LastName

    private String role;//BackEnd?FrontEnd?QA?...

    @Column(name = "is_team_leader")
    private Boolean isTeamLeader;//This: ex(account is a BackEnd TeamLeader)

    @Column(name = "created_at")
    private LocalDateTime createdAt;//time of created this account

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;//any edit on account info
}
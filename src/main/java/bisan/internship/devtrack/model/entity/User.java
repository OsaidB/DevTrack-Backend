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
    private Long userId;

    private String username;

    @Column(name = "email_id" ,nullable = true, unique = true)
    private String email;

    @Column(name = "pass")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String role;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
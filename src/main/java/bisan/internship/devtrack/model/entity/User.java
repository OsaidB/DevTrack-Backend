package bisan.internship.devtrack.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Using IDENTITY for auto-increment
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private FunctionalRole funcRole; // Functional role like BackEnd, FrontEnd, QA

    @Column(name = "is_team_leader", nullable = false)
    private Boolean isTeamLeader; // Specifies if the user is a team leader

    @Column(name = "authorities")
    private String authorities; // Roles such as Admin, Team Leader, Developer

    @Column(name = "last_password_reset")
    private Date lastPasswordReset; // Time when the password was last reset

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Time of account creation

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Last time the account info was updated

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", funcRole=" + funcRole +
                ", isTeamLeader=" + isTeamLeader +
                ", authorities='" + authorities + '\'' +
                ", lastPasswordReset=" + lastPasswordReset +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

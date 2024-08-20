package bisan.internship.devtrack.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity //to make this class as a JPA Entity
@Table(name="users")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;
    private String name;

    @Column(name = "email_id" ,nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "pass", nullable = false)
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Lob
    @Column(length = 1000000)
    private byte[] profilePicture;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private FunctionalRole role;//BackEnd?FrontEnd?QA?...


//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getHashedPassword() {
//        return hashedPassword;
//    }
//
//    public void setHashedPassword(String hashedPassword) {
//        this.hashedPassword = hashedPassword;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Column(name = "is_team_leader", nullable = false)
    private Boolean isTeamLeader;//This: ex(account is a BackEnd TeamLeader)

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;//time of created this account

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;//any edit on account info
}
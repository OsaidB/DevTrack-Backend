package bisan.internship.devtrack.model.entity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "securied_users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User_2 implements Serializable {

  private static final long serialVersionUID = 2353528370345499815L;
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "last_password_reset")
  private Date lastPasswordReset;

  @Column(name = "authorities")
  private String authorities;

  @Override
  public boolean equals(Object o) {
    // Overrides the equals method to compare User_2 objects based on their id
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User_2 user_2 = (User_2) o;
    return id != null && id.equals(user_2.id);
  }

  @Override    // Overrides the hashCode method to generate a hash code based on the id
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    // Overrides the toString method to return a string representation of the User_2 object
    return "User_2{" +
            "id=" + id +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", lastPasswordReset=" + lastPasswordReset +
            ", authorities='" + authorities + '\'' +
            '}';
  }
}

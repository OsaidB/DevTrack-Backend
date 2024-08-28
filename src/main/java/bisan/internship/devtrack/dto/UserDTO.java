package bisan.internship.devtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
//    private String username;

    private String email;
//    private String password;//should not stay here

    private String firstName;
    private String lastName;

    @NotNull(message = "The Role can't be null")
    private Long functionalRoleId; // Reflects the role ID

    private Boolean isTeamLeader;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    public String getPassword() {
//        String pass="tempTemp For Test";
//
//        return pass;
//    }
}

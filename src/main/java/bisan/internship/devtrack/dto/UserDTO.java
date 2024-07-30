package bisan.internship.devtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {  //we use UserDTO class to transfer the data between client and server,
                        //so when we build the rest APIs Services,
                        //we'll use this UserDTO as a response for the rest APIs
    private Long userId;
    private String username;

    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String role;
    private Boolean isAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    public String getPassword() {
//        String pass="tempTemp For Test";
//
//        return pass;
//    }
}

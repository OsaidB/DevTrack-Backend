package bisan.internship.devtrack.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthResponse extends ModelBase {

    private static final long serialVersionUID = 7431193836933783650L;

    private String token;
}

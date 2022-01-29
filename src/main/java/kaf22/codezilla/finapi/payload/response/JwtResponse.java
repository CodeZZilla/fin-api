package kaf22.codezilla.finapi.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
public class JwtResponse {

    private String token;
    private Long id;
    private String fullName;
    private String photo;
    private String login;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String fullName, String photo, String login, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.login = login;
        this.roles = roles;
        this.fullName = fullName;
        this.photo = photo;
    }
}

package kaf22.codezilla.finapi.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@Data
public class JwtResponse {

    private String token;
    private Map<String, Object> currentUser = new HashMap<>();
//    private Long id;
//    private String fullName;
//    private String photo;
//    private String login;
//    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String fullName, String photo, String login, List<String> roles) {
        this.token = accessToken;
//        this.id = id;
//        this.login = login;
//        this.roles = roles;
//        this.fullName = fullName;
//        this.photo = photo;
        this.currentUser.put("id", id);
        this.currentUser.put("fullName", fullName);
        this.currentUser.put("photo", photo);
        this.currentUser.put("login", login);
        this.currentUser.put("roles", roles);
    }
}

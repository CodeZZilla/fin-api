package kaf22.codezilla.finapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
    private String fullName;

    private Set<String> role;
}

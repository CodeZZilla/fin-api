package kaf22.codezilla.finapi.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}


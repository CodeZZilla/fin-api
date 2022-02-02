package kaf22.codezilla.finapi.controllers;

import kaf22.codezilla.finapi.errors.ErrorCode;
import kaf22.codezilla.finapi.models.ERole;
import kaf22.codezilla.finapi.models.Role;
import kaf22.codezilla.finapi.models.User;
import kaf22.codezilla.finapi.payload.request.LoginRequest;
import kaf22.codezilla.finapi.payload.request.SignupRequest;
import kaf22.codezilla.finapi.payload.response.JwtResponse;
import kaf22.codezilla.finapi.repositories.RoleRepository;
import kaf22.codezilla.finapi.repositories.UserRepository;
import kaf22.codezilla.finapi.security.jwt.JwtUtils;
import kaf22.codezilla.finapi.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    JwtUtils jwtUtils;
    PasswordEncoder encoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authorization(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticateUser(loginRequest.getLogin(), loginRequest.getPassword());
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "code", ErrorCode.INCORRECT_LOGIN_DETAILS.getCode(),
                            "error", ErrorCode.INCORRECT_LOGIN_DETAILS.getMessage()));
        }

        User user = new User();
        user.setLogin(signupRequest.getLogin());
        user.setFullName(signupRequest.getFullName());
        user.setPassword(encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return authenticateUser(signupRequest.getLogin(), signupRequest.getPassword());
    }

    private ResponseEntity<?> authenticateUser(String login, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getFullName(),
                    userDetails.getPhoto(),
                    userDetails.getUsername(),
                    roles));
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("code", ErrorCode.INCORRECT_LOGIN_DETAILS.getCode(),
                    "error", ErrorCode.INCORRECT_LOGIN_DETAILS.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

}

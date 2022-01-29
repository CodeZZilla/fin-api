package kaf22.codezilla.finapi.controllers;

import kaf22.codezilla.finapi.controllersAdvice.ExceptionUserController;
import kaf22.codezilla.finapi.errors.ErrorCode;
import kaf22.codezilla.finapi.models.Role;
import kaf22.codezilla.finapi.models.User;
import kaf22.codezilla.finapi.repositories.RoleRepository;
import kaf22.codezilla.finapi.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Value("${upload.path.user.photo}")
    private String pathUploadPhoto;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/get/login/{login}")
    public ResponseEntity<?> getUserByLogin(@PathVariable String login) {
        Optional<User> user = userRepository.findByLogin(login);

        return user.isPresent() ? ResponseEntity.ok(user.get()) : new ResponseEntity<>(Map.of(
                "code", ErrorCode.NO_SUCH_OBJECT.getCode(),
                "error", ErrorCode.NO_SUCH_OBJECT.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);

        return user.isPresent() ? ResponseEntity.ok(user.get()) : new ResponseEntity<>(Map.of(
                "code", ErrorCode.NO_SUCH_OBJECT.getCode(),
                "error", ErrorCode.NO_SUCH_OBJECT.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/photo/{id}")
    public ResponseEntity<?> setPhotoUser(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws FileUploadException {
        Optional<User> user = userRepository.findById(id);

        if (file.isEmpty() || user.isEmpty()) {
            return new ResponseEntity<>(Map.of(
                    "code", ErrorCode.NO_SUCH_OBJECT.getCode(),
                    "message", ErrorCode.NO_SUCH_OBJECT.getMessage()
            ), HttpStatus.NOT_FOUND);
        }

        try {
            Path root = Paths.get(pathUploadPhoto);
            Path resolve = root.resolve(Objects.requireNonNull(file.getOriginalFilename()));

            if (!root.toFile().exists())
                root.toFile().mkdirs();

            if (resolve.toFile().exists()) {
                log.warn("File already exists: " + file.getOriginalFilename());
            } else
                Files.copy(file.getInputStream(), resolve);

        } catch (Exception e) {
            throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
        }

        user.get().setPhoto(pathUploadPhoto + file.getOriginalFilename());
        userRepository.save(user.get());

        return ResponseEntity.ok(Map.of(
                "photo", user.get().getPhoto()
        ));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody User userBody) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            return new ResponseEntity<>(Map.of(
                    "code", ErrorCode.INCORRECT_ID.getCode(),
                    "error", ErrorCode.INCORRECT_ID.getMessage()
            ), HttpStatus.NOT_FOUND);

//        if (userBody == null)
//            return ResponseEntity.badRequest()
//                    .body(Map.of(
//                            "code", ErrorCode.AT_LEAST_ONE_PARAMETER.getCode(),
//                            "error", ErrorCode.AT_LEAST_ONE_PARAMETER.getMessage()
//                    ));

        user.get().setFullName(userBody.getFullName() != null ? userBody.getFullName() : user.get().getFullName());
        user.get().setLogin(userBody.getLogin() != null ? userBody.getLogin() : user.get().getLogin());
        user.get().setPassword(userBody.getPassword() != null ? encoder.encode(userBody.getPassword()) : user.get().getPassword());

        Set<Role> roles = new HashSet<>();
        userBody.getRoles().forEach(item -> {
            Role role = roleRepository.findById(item.getId()).get();
            roles.add(role);
        });

        user.get().setRoles(userBody.getRoles().isEmpty() ? user.get().getRoles() : roles);

        System.out.println(userBody);


        return ResponseEntity.ok().body(user.get());
    }
}

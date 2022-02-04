package kaf22.codezilla.finapi;

import kaf22.codezilla.finapi.models.ERole;
import kaf22.codezilla.finapi.models.Role;
import kaf22.codezilla.finapi.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.Optional;

@SpringBootApplication
public class FinApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinApiApplication.class, args);
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*")
                        .allowedHeaders("*")
                        .allowedOrigins("*");
            }
        };
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
                roleRepository.save(new Role(ERole.ROLE_ADMIN));
            }

            if (roleRepository.findByName(ERole.ROLE_USER).isEmpty())
                roleRepository.save(new Role(ERole.ROLE_USER));

        };
    }

}

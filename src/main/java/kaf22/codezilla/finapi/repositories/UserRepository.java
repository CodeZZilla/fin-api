package kaf22.codezilla.finapi.repositories;

import kaf22.codezilla.finapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Boolean existsByLogin(String login);
}

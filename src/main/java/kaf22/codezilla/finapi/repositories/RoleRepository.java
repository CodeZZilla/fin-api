package kaf22.codezilla.finapi.repositories;

import kaf22.codezilla.finapi.models.ERole;
import kaf22.codezilla.finapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}

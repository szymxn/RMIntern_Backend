package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

    @Override
    void delete(Role role);
}

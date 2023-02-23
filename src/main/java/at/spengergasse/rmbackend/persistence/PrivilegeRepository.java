package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}

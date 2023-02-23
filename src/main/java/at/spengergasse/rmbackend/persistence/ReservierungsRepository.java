package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Reservierung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservierungsRepository extends JpaRepository<Reservierung,Long> {
    Optional<Reservierung> findByName(String name);

    Optional<Reservierung> findByUser_Id(Long user_id);
}

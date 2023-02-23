package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.Reservierungsattribut;
import at.spengergasse.rmbackend.service.dto.ReservierungsattributDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

@Repository
public interface ReservierungsattributRepository extends JpaRepository<Reservierungsattribut,Long> {
    Optional<Reservierungsattribut> findByReservierungId(Long id);
}

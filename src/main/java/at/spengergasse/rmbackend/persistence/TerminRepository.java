package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TerminRepository extends JpaRepository<Termin,Long> {
    Optional<Termin> findByDatum(LocalDate datum);
    Optional<Termin> findByReservierungId(Long id);
}

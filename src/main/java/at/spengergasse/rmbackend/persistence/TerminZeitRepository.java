package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.TerminZeit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

@Repository
public interface TerminZeitRepository extends JpaRepository<TerminZeit,Long> {
     Optional<TerminZeit> findByReservierungId(Long id);
}

package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.KundenZuReservierung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scala.tools.cmd.Opt;

import java.util.Optional;

@Repository
public interface KundenZuReservierungRepository extends JpaRepository<KundenZuReservierung,Long> {
    Optional<KundenZuReservierung> findByUser_IdAndAndTermin_Id(Long user_id, Long termin_id);
    Optional<KundenZuReservierung> findKundenZuReservierungByUser_Email(String email);
}

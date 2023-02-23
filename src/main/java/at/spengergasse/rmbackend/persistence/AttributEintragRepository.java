package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.AttributEintrag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributEintragRepository extends JpaRepository<AttributEintrag,Long> {
    Optional<AttributEintrag> findAttributEintragByEingetragenerWert(String eingetragenerWert);
    Optional<AttributEintrag> findAttributEintragByReservierungsattributIdAndAndKundenzureservierungId(Long attId, Long krId);
}

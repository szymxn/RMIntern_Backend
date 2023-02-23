package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Auditlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<Auditlog,Long> {
}

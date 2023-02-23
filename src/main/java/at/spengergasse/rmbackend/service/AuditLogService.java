package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.Domain.Auditlog;
import at.spengergasse.rmbackend.persistence.AuditLogRepository;
import at.spengergasse.rmbackend.service.dto.command.MutateAuditLogCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public Auditlog createAuditlog(MutateAuditLogCommand auditLogCommand){
        return auditLogRepository.save(Auditlog.builder()
                        .timestamp(auditLogCommand.getTimestamp())
                        .username(auditLogCommand.getUsername())
                        .operation(auditLogCommand.getOperation())
                .build());
    }
}

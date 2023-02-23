package at.spengergasse.rmbackend.service.dto;

import at.spengergasse.rmbackend.Domain.Auditlog;

import java.time.LocalDateTime;

public record AuditLogDto(LocalDateTime timestamp, String username, String operation) {
    public AuditLogDto(Auditlog auditlog){
        this(auditlog.getTimestamp(),auditlog.getUsername(),auditlog.getOperation());
    }
}

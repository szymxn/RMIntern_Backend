package at.spengergasse.rmbackend.service.dto.command;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateAuditLogCommand {
    private LocalDateTime timestamp;

    private String username;

    private String operation;
}

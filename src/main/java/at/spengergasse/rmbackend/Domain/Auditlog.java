package at.spengergasse.rmbackend.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditlog")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Auditlog extends AbstractPersistable<Long> {

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name="username")
    private String username;

    @Column(name="operation")
    private String operation;
}

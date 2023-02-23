package at.spengergasse.rmbackend.Domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Entity
@Table(name = "terminzeit")
public class TerminZeit extends AbstractPersistable <Long> {
    private String TerminZeitStart;
    private String TerminZeitEnde;
    private String Wochentag;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Reservierung reservierung;

    private String token;

    @Override
    public String toString() {
        return "TerminZeit{" +
                "TerminZeitStart='" + TerminZeitStart + '\'' +
                ", TerminZeitEnde='" + TerminZeitEnde + '\'' +
                ", Wochentag='" + Wochentag + '\'' +
                '}';
    }
}

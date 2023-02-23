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
@Table(name ="attributeintrag")
public class AttributEintrag extends AbstractPersistable<Long> {
    private String eingetragenerWert;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Reservierungsattribut reservierungsattribut;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private KundenZuReservierung kundenzureservierung;

    private String token;

    @Override
    public String toString() {
        return "AttributEintrag{" +
                "EingetragenerWert='" + eingetragenerWert + '\'' +
                '}';
    }




}

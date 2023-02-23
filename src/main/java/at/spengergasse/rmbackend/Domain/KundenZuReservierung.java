package at.spengergasse.rmbackend.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Entity
@Table(name = "kundenzureservierung")
public class KundenZuReservierung extends AbstractPersistable<Long> {
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Termin termin;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private User user;
    @OneToMany(mappedBy = "kundenzureservierung",cascade = CascadeType.PERSIST)
    private List<AttributEintrag> attributEintragList = new ArrayList<>();

    private String token;

@Builder
    public KundenZuReservierung(Termin termin, User user) {
        this.termin = termin;
        this.user = user;
    }

    @Override
    public String toString() {
        return "KundenZuReservierung{" +
                "termin=" + termin +
                '}';
    }
}

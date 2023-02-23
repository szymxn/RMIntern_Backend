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
@Table(name = "reservierungsattribut")
public class Reservierungsattribut extends AbstractPersistable<Long> {
    private String Bezeichnung;
    private String Datentyp;
    private String Zeichenlaenge;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Reservierung reservierung;
    @OneToMany(mappedBy = "reservierungsattribut",cascade = CascadeType.PERSIST)
    private List<AttributEintrag> attributEintragList = new ArrayList<>();

    private String token;

    @Builder
    public Reservierungsattribut(String bezeichnung, String datentyp, String zeichenlaenge, Reservierung reservierung) {
        this.Bezeichnung = bezeichnung;
        this.Datentyp = datentyp;
        this. Zeichenlaenge = zeichenlaenge;
        this.reservierung = reservierung;
    }

    @Override
    public String toString() {
        return "Reservierungsattribut{" +
                "Bezeichnung='" + Bezeichnung + '\'' +
                ", Datentyp='" + Datentyp + '\'' +
                ", Zeichenlaenge='" + Zeichenlaenge + '\'' +
                '}';
    }
}

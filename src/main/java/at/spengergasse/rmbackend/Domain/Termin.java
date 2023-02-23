package at.spengergasse.rmbackend.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Entity
@Table(name = "termin")
public class Termin extends AbstractPersistable<Long> {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datum;
    private String ZeitVon;
    private String ZeitBis;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Reservierung reservierung;
    @OneToMany(mappedBy = "termin",cascade = CascadeType.PERSIST)
    private List<KundenZuReservierung> kundenZuReservierungList = new ArrayList<>();

    private String token;

    @Builder
    public Termin(LocalDate datum, String zeitVon, String zeitBis, Reservierung reservierung) {
        this.datum = datum;
        this.ZeitVon = zeitVon;
        this.ZeitBis = zeitBis;
        this.reservierung = reservierung;
    }

    @Override
    public String toString() {
        return "Termin{" +
                "datum=" + datum +
                ", ZeitVon='" + ZeitVon + '\'' +
                ", ZeitBis='" + ZeitBis + '\'' +
                '}';
    }
}

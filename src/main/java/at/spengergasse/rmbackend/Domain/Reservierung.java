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
@Entity
@Builder
@Table(name = "resevierung")
public class Reservierung extends AbstractPersistable<Long> {
    private String name;
    private String terminlaenge;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private User user;
    @OneToMany(mappedBy ="reservierung", fetch = FetchType.LAZY)
    private List<TerminZeit> terminZeitList = new ArrayList<>();

    @OneToMany(mappedBy ="reservierung",fetch = FetchType.LAZY)
    private List<Reservierungsattribut> reservierungsattributs = new ArrayList<>();

    @OneToMany(mappedBy = "reservierung", fetch = FetchType.LAZY)
    private List<Termin> termins =new ArrayList<>();

    private String token;

    private boolean isBlocked;


    public Reservierung(String name, String terminlaenge, User user, List<Reservierungsattribut> reservierungsattributs, String token, boolean isBlocked) {
        this.name = name;
        this.terminlaenge = terminlaenge;
        this.user = user;
        this.reservierungsattributs = reservierungsattributs;
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        return "Reservierung{" +
                "name='" + name + '\'' +
                ", terminlaenge='" + terminlaenge + '\'' +
                ", user=" + user +
                '}';
    }
}

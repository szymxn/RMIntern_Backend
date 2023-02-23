package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.Reservierungsattribut;
import at.spengergasse.rmbackend.Domain.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReservierungattributRepositoryTest {
    @Autowired
    private ReservierungsattributRepository repository;

    @Test
    void ensureSaveWorksProbably(){
        Reservierungsattribut reservierungsattribut = Reservierungsattribut.builder()
                .Bezeichnung("xxxxxx")
                .Datentyp("Integer")
                .Zeichenlaenge("12")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("12 Sekunden")
                        .user(User.builder()
                                .vorname("xxx")
                                .nachname("xxx")
                                .email("sxxx@gmail.com")
                                .username("ssssss")
                                .password("xxxxxx")
                                .isUsing2FA(false)
                                .enabled(true)
                                .roles(null)
                                .build())
                        .isBlocked(true)
                        .build())
                .build();

        var saved = repository.save(reservierungsattribut);
        assertThat(saved).isSameAs(reservierungsattribut);
        assertThat(saved.getId()).isSameAs(reservierungsattribut.getId());
        assertThat(saved.getBezeichnung()).isEqualTo(reservierungsattribut.getBezeichnung());
    }

    @Test
    void ensureDeletingWorksProbably(){
        Reservierungsattribut reservierungsattribut = Reservierungsattribut.builder()
                .Bezeichnung("xxxxxx")
                .Datentyp("Integer")
                .Zeichenlaenge("12")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("12 Sekunden")
                        .user(User.builder()
                                .vorname("xxx")
                                .nachname("xxx")
                                .email("sxxx@gmail.com")
                                .username("ssssss")
                                .password("xxxxxx")
                                .isUsing2FA(false)
                                .enabled(true)
                                .roles(null)
                                .build())
                        .isBlocked(true)
                        .build())
                .build();

        var saved = repository.save(reservierungsattribut);

        repository.delete(reservierungsattribut);

        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    void ensureFindingReservationAttrbuteByIdWorksProbably(){
        Reservierungsattribut reservierungsattribut = Reservierungsattribut.builder()
                .Bezeichnung("xxxxxx")
                .Datentyp("Integer")
                .Zeichenlaenge("12")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("12 Sekunden")
                        .user(User.builder()
                                .vorname("xxx")
                                .nachname("xxx")
                                .email("sxxx@gmail.com")
                                .username("ssssss")
                                .password("xxxxxx")
                                .isUsing2FA(false)
                                .enabled(true)
                                .roles(null)
                                .build())
                        .isBlocked(true)
                        .build())
                .build();

        var saved = repository.save(reservierungsattribut);
        var finding = repository.findById(reservierungsattribut.getId());

        assertThat(saved).isSameAs(reservierungsattribut);
        assertThat(finding.get().getId()).isEqualTo(reservierungsattribut.getId());
    }

    @Test
    void isReservationAttributeExistsById(){
        Reservierungsattribut reservierungsattribut = Reservierungsattribut.builder()
                .Bezeichnung("xxxxxx")
                .Datentyp("Integer")
                .Zeichenlaenge("12")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("12 Sekunden")
                        .user(User.builder()
                                .vorname("xxx")
                                .nachname("xxx")
                                .email("sxxx@gmail.com")
                                .username("ssssss")
                                .password("xxxxxx")
                                .isUsing2FA(false)
                                .enabled(true)
                                .roles(null)
                                .build())
                        .isBlocked(true)
                        .build())
                .build();

        repository.save(reservierungsattribut);

        boolean actual = repository.existsById(reservierungsattribut.getId());
        assertThat(actual).isTrue();
    }

    @Test
    void ensureUpdatingReservationAttributeWorksProbably(){
        Reservierungsattribut reservierungsattribut = Reservierungsattribut.builder()
                .Bezeichnung("xxxxxx")
                .Datentyp("Integer")
                .Zeichenlaenge("12")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("12 Sekunden")
                        .user(User.builder()
                                .vorname("xxx")
                                .nachname("xxx")
                                .email("sxxx@gmail.com")
                                .username("ssssss")
                                .password("xxxxxx")
                                .isUsing2FA(false)
                                .enabled(true)
                                .roles(null)
                                .build())
                        .isBlocked(true)
                        .build())
                .build();

        repository.save(reservierungsattribut);

        reservierungsattribut.setDatentyp("String");

        var updated = repository.save(reservierungsattribut);
        assertThat(updated).isSameAs(reservierungsattribut);
    }

    @Test
    void ensureInsertingMoreReservationAttributesWorksProbably(){
        Reservierungsattribut r1 = Reservierungsattribut.builder()
                .Bezeichnung("xxxxxx")
                .Datentyp("Integer")
                .Zeichenlaenge("12")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("12 Sekunden")
                        .user(User.builder()
                                .vorname("xxx")
                                .nachname("xxx")
                                .email("sxxx@gmail.com")
                                .username("ssssss")
                                .password("xxxxxx")
                                .isUsing2FA(false)
                                .enabled(true)
                                .roles(null)
                                .build())
                        .isBlocked(true)
                        .build())
                .build();

        Reservierungsattribut r2 = Reservierungsattribut.builder()
                .Bezeichnung("yyyyyyy")
                .Datentyp("ffffff")
                .Zeichenlaenge("89")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("12 Sekunden")
                        .user(User.builder()
                                .vorname("xxx")
                                .nachname("xxx")
                                .email("ddddddd@gmail.com")
                                .username("ssssss")
                                .password("xxxxxx")
                                .isUsing2FA(false)
                                .enabled(true)
                                .roles(null)
                                .build())
                        .isBlocked(true)
                        .build())
                .build();

        var saved = repository.saveAll(List.of(r1,r2));

        assertThat(saved.size()).isGreaterThan(1);
    }
}

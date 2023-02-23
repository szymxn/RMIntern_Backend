package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.Termin;
import at.spengergasse.rmbackend.Domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TerminRepositoryTest {
    @Autowired
    private TerminRepository terminRepository;

    @Test
    void ensureSaveWorksProbably(){
        Termin termin = Termin.builder()
                .datum(LocalDate.of(2020,04,11))
                .ZeitVon("10:00")
                .ZeitBis("12:30")
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

        var saved = terminRepository.save(termin);
        assertThat(saved).isSameAs(termin);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getDatum()).isSameAs(termin.getDatum());
    }

    @Test
    void ensureDeletingWorksProbably(){
        Termin termin = Termin.builder()
                .datum(LocalDate.of(2020,04,11))
                .ZeitVon("10:00")
                .ZeitBis("12:30")
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

        var saved = terminRepository.save(termin);

        terminRepository.delete(termin);

        assertThat(terminRepository.count()).isEqualTo(0);
    }

    @Test
    void ensureFindingTerminById(){
        Termin termin = Termin.builder()
                .datum(LocalDate.of(2020,04,11))
                .ZeitVon("10:00")
                .ZeitBis("12:30")
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

        var saved = terminRepository.save(termin);
        var finding = terminRepository.findById(termin.getId());

        assertThat(saved).isSameAs(termin);
        assertThat(finding.get().getId()).isEqualTo(termin.getId());
    }

    @Test
    void isTerminExistsById(){
        Termin termin = Termin.builder()
                .datum(LocalDate.of(2020,04,11))
                .ZeitVon("10:00")
                .ZeitBis("12:30")
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

        terminRepository.save(termin);

        boolean actual = terminRepository.existsById(termin.getId());
        assertThat(actual).isTrue();
    }

    @Test
    void ensureUpdatingTerminWorksProbably(){
        Termin termin = Termin.builder()
                .datum(LocalDate.of(2020,04,11))
                .ZeitVon("10:00")
                .ZeitBis("12:30")
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

        terminRepository.save(termin);

        termin.setZeitVon("09:00");

        var updated = terminRepository.save(termin);
        assertThat(updated).isSameAs(termin);
    }

    @Test
    void ensureInsertingMoreTerminWorksProbably(){
        Termin t1 = Termin.builder()
                .datum(LocalDate.of(2020,04,11))
                .ZeitVon("10:00")
                .ZeitBis("12:30")
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

        Termin t2 = Termin.builder()
                .datum(LocalDate.of(2010,04,11))
                .ZeitVon("18:00")
                .ZeitBis("19:30")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("10 Minuten")
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

        var saved = terminRepository.saveAll(List.of(t1,t2));

        assertThat(saved.size()).isGreaterThan(1);

    }
}

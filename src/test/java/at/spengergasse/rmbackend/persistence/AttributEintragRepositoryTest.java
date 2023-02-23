package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.*;
import clojure.test$run_all_tests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class AttributEintragRepositoryTest {
    @Autowired
    private AttributEintragRepository attributEintragRepository;

    @Test
    void ensureSaveWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
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
                .build();

        User user = User.builder()
                .vorname("xxx")
                .nachname("xxx")
                .email("sxxx@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();

        AttributEintrag eintrag = AttributEintrag.builder()
                .eingetragenerWert("xxx")
                .reservierungsattribut(Reservierungsattribut.builder()
                        .Bezeichnung("xxxxxx")
                        .Datentyp("Integer")
                        .Zeichenlaenge("12")
                        .reservierung(reservierung)
                        .build())
                .kundenzureservierung(KundenZuReservierung.builder()
                        .termin(Termin.builder()
                                .datum(LocalDate.of(2010,04,11))
                                .ZeitVon("18:00")
                                .ZeitBis("19:30")
                                .reservierung(reservierung)
                                .build())
                        .user(user)
                        .build())
                .build();

        var saved = attributEintragRepository.save(eintrag);
        assertThat(saved).isSameAs(eintrag);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getEingetragenerWert()).isSameAs(eintrag.getEingetragenerWert());
    }

    @Test
    void ensureDeletingWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
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
                .build();

        User user = User.builder()
                .vorname("xxx")
                .nachname("xxx")
                .email("sxxx@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();

        AttributEintrag eintrag = AttributEintrag.builder()
                .eingetragenerWert("xxx")
                .reservierungsattribut(Reservierungsattribut.builder()
                        .Bezeichnung("xxxxxx")
                        .Datentyp("Integer")
                        .Zeichenlaenge("12")
                        .reservierung(reservierung)
                        .build())
                .kundenzureservierung(KundenZuReservierung.builder()
                        .termin(Termin.builder()
                                .datum(LocalDate.of(2010,04,11))
                                .ZeitVon("18:00")
                                .ZeitBis("19:30")
                                .reservierung(reservierung)
                                .build())
                        .user(user)
                        .build())
                .build();

        var saved = attributEintragRepository.save(eintrag);
        attributEintragRepository.delete(eintrag);

        assertThat(attributEintragRepository.count()).isEqualTo(0);
    }

    @Test
    void ensureFindingAttributEintragByIdWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
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
                .build();

        User user = User.builder()
                .vorname("xxx")
                .nachname("xxx")
                .email("sxxx@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();

        AttributEintrag eintrag = AttributEintrag.builder()
                .eingetragenerWert("xxx")
                .reservierungsattribut(Reservierungsattribut.builder()
                        .Bezeichnung("xxxxxx")
                        .Datentyp("Integer")
                        .Zeichenlaenge("12")
                        .reservierung(reservierung)
                        .build())
                .kundenzureservierung(KundenZuReservierung.builder()
                        .termin(Termin.builder()
                                .datum(LocalDate.of(2010,04,11))
                                .ZeitVon("18:00")
                                .ZeitBis("19:30")
                                .reservierung(reservierung)
                                .build())
                        .user(user)
                        .build())
                .build();

        var saved = attributEintragRepository.save(eintrag);
        var finding = attributEintragRepository.findById(eintrag.getId());

        assertThat(saved).isSameAs(eintrag);
        assertThat(finding.get().getId()).isEqualTo(eintrag.getId());
    }

    @Test
    void isAttribuEintragExistsById(){

        Reservierung reservierung = Reservierung.builder()
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
                .build();

        User user = User.builder()
                .vorname("xxx")
                .nachname("xxx")
                .email("sxxx@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();

        AttributEintrag eintrag = AttributEintrag.builder()
                .eingetragenerWert("xxx")
                .reservierungsattribut(Reservierungsattribut.builder()
                        .Bezeichnung("xxxxxx")
                        .Datentyp("Integer")
                        .Zeichenlaenge("12")
                        .reservierung(reservierung)
                        .build())
                .kundenzureservierung(KundenZuReservierung.builder()
                        .termin(Termin.builder()
                                .datum(LocalDate.of(2010,04,11))
                                .ZeitVon("18:00")
                                .ZeitBis("19:30")
                                .reservierung(reservierung)
                                .build())
                        .user(user)
                        .build())
                .build();

        attributEintragRepository.save(eintrag);

        boolean actual = attributEintragRepository.existsById(eintrag.getId());
        assertThat(actual).isTrue();
    }

    @Test
    void ensureUpdatingAttributEintragWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
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
                .build();

        User user = User.builder()
                .vorname("xxx")
                .nachname("xxx")
                .email("sxxx@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();

        AttributEintrag eintrag = AttributEintrag.builder()
                .eingetragenerWert("xxx")
                .reservierungsattribut(Reservierungsattribut.builder()
                        .Bezeichnung("xxxxxx")
                        .Datentyp("Integer")
                        .Zeichenlaenge("12")
                        .reservierung(reservierung)
                        .build())
                .kundenzureservierung(KundenZuReservierung.builder()
                        .termin(Termin.builder()
                                .datum(LocalDate.of(2010,04,11))
                                .ZeitVon("18:00")
                                .ZeitBis("19:30")
                                .reservierung(reservierung)
                                .build())
                        .user(user)
                        .build())
                .build();

        attributEintragRepository.save(eintrag);

        eintrag.getReservierungsattribut().setZeichenlaenge("hahaha");

        var updated = attributEintragRepository.save(eintrag);
        assertThat(updated).isSameAs(eintrag);
    }

    @Test
    void ensureInsertingMoreAttribuEintragWorksProbably(){
        Reservierung reservierung1 = Reservierung.builder()
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
                .build();

        User user1 = User.builder()
                .vorname("xxx")
                .nachname("xxx")
                .email("sxxx@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();

        AttributEintrag eintrag1 = AttributEintrag.builder()
                .eingetragenerWert("xxx")
                .reservierungsattribut(Reservierungsattribut.builder()
                        .Bezeichnung("xxxxxx")
                        .Datentyp("Integer")
                        .Zeichenlaenge("12")
                        .reservierung(reservierung1)
                        .build())
                .kundenzureservierung(KundenZuReservierung.builder()
                        .termin(Termin.builder()
                                .datum(LocalDate.of(2010,04,11))
                                .ZeitVon("18:00")
                                .ZeitBis("19:30")
                                .reservierung(reservierung1)
                                .build())
                        .user(user1)
                        .build())
                .build();


        Reservierung reservierung = Reservierung.builder()
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
                .build();

        User user = User.builder()
                .vorname("xxx")
                .nachname("xxx")
                .email("sxxx@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();

        AttributEintrag eintrag = AttributEintrag.builder()
                .eingetragenerWert("xxx")
                .reservierungsattribut(Reservierungsattribut.builder()
                        .Bezeichnung("xxxxxx")
                        .Datentyp("Integer")
                        .Zeichenlaenge("12")
                        .reservierung(reservierung)
                        .build())
                .kundenzureservierung(KundenZuReservierung.builder()
                        .termin(Termin.builder()
                                .datum(LocalDate.of(2010,04,11))
                                .ZeitVon("18:00")
                                .ZeitBis("19:30")
                                .reservierung(reservierung)
                                .build())
                        .user(user)
                        .build())
                .build();

        var saved = attributEintragRepository.saveAll(List.of(eintrag1,eintrag));

        assertThat(saved.size()).isGreaterThan(1);

    }

}

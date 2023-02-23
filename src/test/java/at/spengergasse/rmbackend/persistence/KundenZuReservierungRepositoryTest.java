package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.KundenZuReservierung;
import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.Termin;
import at.spengergasse.rmbackend.Domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class KundenZuReservierungRepositoryTest {
    @Autowired
    private KundenZuReservierungRepository kundenZuReservierungRepository;
    @Autowired
    private AttributEintragRepository attributEintragRepository;

    @Test
    void ensureSaveWorksProbably(){
        User user = User.builder()
                .vorname("Sel")
                .nachname("Tan")
                .email("ggg@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();
        KundenZuReservierung kundenZuReservierung = KundenZuReservierung.builder()
                .user(user)
                .termin(Termin.builder()
                        .datum(LocalDate.of(2010,1,1))
                        .ZeitVon("10:11")
                        .ZeitBis("11:25")
                        .reservierung(Reservierung.builder()
                                .name("xxxxx")
                                .terminlaenge("12 Minutes")
                                .user(user)
                                .isBlocked(false)
                                .build())
                        .build())
                .build();

        var saved = kundenZuReservierungRepository.save(kundenZuReservierung);
        assertThat(saved).isSameAs(kundenZuReservierung);
        assertThat(saved.getId()).isSameAs(kundenZuReservierung.getId());
        assertThat(saved.getUser()).isSameAs(kundenZuReservierung.getUser());
    }

    @Test
    void ensureDeletingWorksProbably(){
        User user = User.builder()
                .vorname("Sel")
                .nachname("Tan")
                .email("ggg@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();
        KundenZuReservierung kundenZuReservierung = KundenZuReservierung.builder()
                .user(user)
                .termin(Termin.builder()
                        .datum(LocalDate.of(2010,1,1))
                        .ZeitVon("10:11")
                        .ZeitBis("11:25")
                        .reservierung(Reservierung.builder()
                                .name("xxxxx")
                                .terminlaenge("12 Minutes")
                                .user(user)
                                .isBlocked(false)
                                .build())
                        .build())
                .build();

        var saved = kundenZuReservierungRepository.save(kundenZuReservierung);
        kundenZuReservierungRepository.delete(kundenZuReservierung);

        assertThat(kundenZuReservierungRepository.count()).isEqualTo(0);
    }

    @Test
    void ensureFindingKRByIdWorksProbably(){
        User user = User.builder()
                .vorname("Sel")
                .nachname("Tan")
                .email("ggg@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();
        KundenZuReservierung kundenZuReservierung = KundenZuReservierung.builder()
                .user(user)
                .termin(Termin.builder()
                        .datum(LocalDate.of(2010,1,1))
                        .ZeitVon("10:11")
                        .ZeitBis("11:25")
                        .reservierung(Reservierung.builder()
                                .name("xxxxx")
                                .terminlaenge("12 Minutes")
                                .user(user)
                                .isBlocked(false)
                                .build())
                        .build())
                .build();

        var saved = kundenZuReservierungRepository.save(kundenZuReservierung);
        var finding = kundenZuReservierungRepository.findById(kundenZuReservierung.getId());

        assertThat(saved).isSameAs(kundenZuReservierung);
        assertThat(finding.get().getId()).isEqualTo(kundenZuReservierung.getId());
    }

    @Test
    void ensureUpdatingKR(){
        User user = User.builder()
                .vorname("Sel")
                .nachname("Tan")
                .email("ggg@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();
        KundenZuReservierung kundenZuReservierung = KundenZuReservierung.builder()
                .user(user)
                .termin(Termin.builder()
                        .datum(LocalDate.of(2010,1,1))
                        .ZeitVon("10:11")
                        .ZeitBis("11:25")
                        .reservierung(Reservierung.builder()
                                .name("xxxxx")
                                .terminlaenge("12 Minutes")
                                .user(user)
                                .isBlocked(false)
                                .build())
                        .build())
                .build();

        kundenZuReservierungRepository.save(kundenZuReservierung);

        kundenZuReservierung.getTermin().setDatum(LocalDate.now());

        var updated = kundenZuReservierungRepository.save(kundenZuReservierung);

        assertThat(updated).isSameAs(kundenZuReservierung);
    }

    @Test
    void ensureInsertingMoreKRsWorksProbably(){
        User user1 = User.builder()
                .vorname("Sel")
                .nachname("Tan")
                .email("ggg@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();
        KundenZuReservierung kundenZuReservierung1 = KundenZuReservierung.builder()
                .user(user1)
                .termin(Termin.builder()
                        .datum(LocalDate.of(2010,1,1))
                        .ZeitVon("10:11")
                        .ZeitBis("11:25")
                        .reservierung(Reservierung.builder()
                                .name("xxxxx")
                                .terminlaenge("12 Minutes")
                                .user(user1)
                                .isBlocked(false)
                                .build())
                        .build())
                .build();

        User user = User.builder()
                .vorname("Sel")
                .nachname("Tan")
                .email("ggg@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();
        KundenZuReservierung kundenZuReservierung = KundenZuReservierung.builder()
                .user(user)
                .termin(Termin.builder()
                        .datum(LocalDate.of(2010,1,1))
                        .ZeitVon("10:11")
                        .ZeitBis("11:25")
                        .reservierung(Reservierung.builder()
                                .name("xxxxx")
                                .terminlaenge("12 Minutes")
                                .user(user)
                                .isBlocked(false)
                                .build())
                        .build())
                .build();


        var saved = kundenZuReservierungRepository.saveAll(List.of(kundenZuReservierung1,kundenZuReservierung));

        assertThat(saved.size()).isGreaterThan(1);

    }
}

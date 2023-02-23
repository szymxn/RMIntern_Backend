package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReservierungRepositoryTest {
    @Autowired
    private ReservierungsRepository reservierungsRepository;


    @Test
    void ensureSaveWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
                .name("xxxx")
                .terminlaenge("12 Minuten")
                .user(User.builder()
                        .vorname("Sel")
                        .nachname("Tan")
                        .email("ggg@gmail.com")
                        .username("ssssss")
                        .password("xxxxxx")
                        .isUsing2FA(false)
                        .enabled(true)
                        .roles(null)
                        .build())
                .isBlocked(true)
                .build();

        var saved = reservierungsRepository.save(reservierung);
        assertThat(saved).isSameAs(reservierung);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isSameAs(reservierung.getName());
    }

    @Test
    void ensureDeletingWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
                .name("xxxx")
                .terminlaenge("12 Minuten")
                .user(User.builder()
                        .vorname("Sel")
                        .nachname("Tan")
                        .email("ggg@gmail.com")
                        .username("ssssss")
                        .password("xxxxxx")
                        .isUsing2FA(false)
                        .enabled(true)
                        .roles(null)
                        .build())
                .isBlocked(true)
                .build();

        var saved = reservierungsRepository.save(reservierung);

        reservierungsRepository.delete(reservierung);

        assertThat(reservierungsRepository.count()).isEqualTo(0);
    }

    @Test
    void ensureFindingReservationByNameWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
                .name("xxxx")
                .terminlaenge("12 Minuten")
                .user(User.builder()
                        .vorname("Sel")
                        .nachname("Tan")
                        .email("ggg@gmail.com")
                        .username("ssssss")
                        .password("xxxxxx")
                        .isUsing2FA(false)
                        .enabled(true)
                        .roles(null)
                        .build())
                .isBlocked(true)
                .build();

        var saved = reservierungsRepository.save(reservierung);
        var finding = reservierungsRepository.findByName(reservierung.getName());

        assertThat(saved).isSameAs(reservierung);
        assertThat(finding.get().getName()).isEqualTo(reservierung.getName());
    }

    @Test
    void ensureFindingReservationByIdWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
                .name("xxxx")
                .terminlaenge("12 Minuten")
                .user(User.builder()
                        .vorname("Sel")
                        .nachname("Tan")
                        .email("ggg@gmail.com")
                        .username("ssssss")
                        .password("xxxxxx")
                        .isUsing2FA(false)
                        .enabled(true)
                        .roles(null)
                        .build())
                .isBlocked(true)
                .build();

        var saved = reservierungsRepository.save(reservierung);
        var finding = reservierungsRepository.findById(reservierung.getId());

        assertThat(saved).isSameAs(reservierung);
        assertThat(finding.get().getId()).isEqualTo(reservierung.getId());
    }

    @Test
    void isReservationExistsbyId(){
        Reservierung reservierung = Reservierung.builder()
                .name("xxxx")
                .terminlaenge("12 Minuten")
                .user(User.builder()
                        .vorname("Sel")
                        .nachname("Tan")
                        .email("ggg@gmail.com")
                        .username("ssssss")
                        .password("xxxxxx")
                        .isUsing2FA(false)
                        .enabled(true)
                        .roles(null)
                        .build())
                .isBlocked(true)
                .build();

        reservierungsRepository.save(reservierung);
        boolean actual = reservierungsRepository.existsById(reservierung.getId());

        assertThat(actual).isTrue();

    }

    @Test
    void ensureUpdatingReservationWorksProbably(){
        Reservierung reservierung = Reservierung.builder()
                .name("xxxx")
                .terminlaenge("12 Minuten")
                .user(User.builder()
                        .vorname("Sel")
                        .nachname("Tan")
                        .email("ggg@gmail.com")
                        .username("ssssss")
                        .password("xxxxxx")
                        .isUsing2FA(false)
                        .enabled(true)
                        .roles(null)
                        .build())
                .isBlocked(true)
                .build();

        reservierungsRepository.save(reservierung);

        reservierung.setTerminlaenge("15 Minuten");

        var updated = reservierungsRepository.save(reservierung);
        assertThat(updated).isSameAs(reservierung);
    }

    @Test
    void ensureInsertingMoreReservationsWorksProbably(){
        Reservierung reservierung1 = Reservierung.builder()
                .name("xxxx")
                .terminlaenge("12 Minuten")
                .user(User.builder()
                        .vorname("Sel")
                        .nachname("Tan")
                        .email("ggg@gmail.com")
                        .username("ssssss")
                        .password("xxxxxx")
                        .isUsing2FA(false)
                        .enabled(true)
                        .roles(null)
                        .build())
                .isBlocked(true)
                .build();

        Reservierung reservierung2 = Reservierung.builder()
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

        var saved = reservierungsRepository.saveAll(List.of(reservierung1,reservierung2));

        assertThat(saved.size()).isGreaterThan(1);
    }
}

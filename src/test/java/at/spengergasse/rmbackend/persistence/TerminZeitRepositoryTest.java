package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.TerminZeit;
import at.spengergasse.rmbackend.Domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TerminZeitRepositoryTest {
    @Autowired
    private TerminZeitRepository terminZeitRepository;

    @Test
    void ensureSaveWorksProbably(){
        TerminZeit terminZeit = TerminZeit.builder()
                .TerminZeitStart("08:00")
                .TerminZeitEnde("16:05")
                .Wochentag("Montag")
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

        var saved = terminZeitRepository.save(terminZeit);
        assertThat(saved).isSameAs(terminZeit);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getId()).isSameAs(terminZeit.getId());
    }

    @Test
    void ensureDeletingWorksProbably(){
        TerminZeit terminZeit = TerminZeit.builder()
                .TerminZeitStart("08:00")
                .TerminZeitEnde("16:05")
                .Wochentag("Montag")
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

        var saved = terminZeitRepository.save(terminZeit);
        terminZeitRepository.delete(terminZeit);

        assertThat(terminZeitRepository.count()).isEqualTo(0);
    }

    @Test
    void ensureFindingTerminZeitById(){
        TerminZeit terminZeit = TerminZeit.builder()
                .TerminZeitStart("08:00")
                .TerminZeitEnde("16:05")
                .Wochentag("Montag")
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

        var saved = terminZeitRepository.save(terminZeit);
        var finding = terminZeitRepository.findById(terminZeit.getId());

        assertThat(saved).isSameAs(terminZeit);
        assertThat(finding.get().getId()).isEqualTo(terminZeit.getId());
    }

    @Test
    void isTerminZeitExistsById(){
        TerminZeit terminZeit = TerminZeit.builder()
                .TerminZeitStart("08:00")
                .TerminZeitEnde("16:05")
                .Wochentag("Montag")
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

        terminZeitRepository.save(terminZeit);
        boolean actual = terminZeitRepository.existsById(terminZeit.getId());
        assertThat(actual).isTrue();
    }

    @Test
    void ensureUpdatingTerminZeitWorksProbably(){
        TerminZeit terminZeit = TerminZeit.builder()
                .TerminZeitStart("08:00")
                .TerminZeitEnde("16:05")
                .Wochentag("Montag")
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

        terminZeitRepository.save(terminZeit);

        terminZeit.setWochentag("Freitag");

        var updated = terminZeitRepository.save(terminZeit);
        assertThat(updated).isSameAs(terminZeit);
    }

    @Test
    void ensureInsertingMoreTerminZeitWorksProbably(){
        TerminZeit t1 = TerminZeit.builder()
                .TerminZeitStart("08:00")
                .TerminZeitEnde("16:05")
                .Wochentag("Montag")
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

        TerminZeit t2 = TerminZeit.builder()
                .TerminZeitStart("11:00")
                .TerminZeitEnde("13:05")
                .Wochentag("Dienstag")
                .reservierung(Reservierung.builder()
                        .name("xxxx")
                        .terminlaenge("12 Minuten")
                        .user(User.builder()
                                .vorname("xxx")
                                .nachname("xxx")
                                .email("sel@gmail.com")
                                .username("ssssss")
                                .password("xxxxxx")
                                .isUsing2FA(false)
                                .enabled(true)
                                .roles(null)
                                .build())
                        .isBlocked(true)
                        .build())
                .build();

        var saved = terminZeitRepository.saveAll(List.of(t1,t2));

        assertThat(saved.size()).isGreaterThan(1);
    }
}

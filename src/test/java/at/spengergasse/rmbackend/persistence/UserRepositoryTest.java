package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLocationRepository userLocationRepository;

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

        var saved = userRepository.save(user);
        assertThat(saved).isSameAs(user);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getVorname()).isSameAs(user.getVorname());
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

        var saved = userRepository.save(user);
        userRepository.delete(user);

        assertThat(userRepository.count()).isEqualTo(0);
    }

    @Test
    void enusreFindingUserByEmailWorksProbably(){
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

        var saved = userRepository.save(user);
        var finding = userRepository.findByEmail(user.getEmail());

        assertThat(saved).isSameAs(user);
        assertThat(finding.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void enusureFindingUserByIdWorksProbably(){
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

        var saved = userRepository.save(user);
        var finding = userRepository.findById(user.getId());

        assertThat(saved).isSameAs(user);
        assertThat(finding.get().getId()).isEqualTo(user.getId());
    }

    @Test
    void isUserExistsById(){
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

       userRepository.save(user);

       boolean actual = userRepository.existsById(user.getId());
       assertThat(actual).isTrue();
    }

    @Test
    void ensureUpdatingUserWorksProbably(){
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

        userRepository.save(user);

        user.setEmail("xxxx@gmx.at");

        var updated = userRepository.save(user);
        assertThat(updated).isSameAs(user);
    }

    @Test
    void enusureInsertingMoreUserWorksProbably(){
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

        User user2 = User.builder()
                .vorname("HHHH")
                .nachname("XXX")
                .email("ggg@gmail.com")
                .username("ssssss")
                .password("xxxxxx")
                .isUsing2FA(false)
                .enabled(true)
                .roles(null)
                .build();

        var saved = userRepository.saveAll(List.of(user1,user2));

        assertThat(saved.size()).isGreaterThan(1);
    }
}

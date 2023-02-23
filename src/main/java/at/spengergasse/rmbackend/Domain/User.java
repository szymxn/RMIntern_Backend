package at.spengergasse.rmbackend.Domain;

import at.spengergasse.rmbackend.foundation.NanoIdFactory;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User extends AbstractPersistable<Long> {
    private String vorname;
    private String nachname;
    private String email;
    private String username;
    @Column(length = 60)
    private String password;

    private boolean enabled;

    private boolean isUsing2FA;

    private String secret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;


    @OneToMany(mappedBy ="user",cascade = CascadeType.PERSIST)
    private List<Reservierung> reservierungs = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private List<KundenZuReservierung> kundenZuReservierungs = new ArrayList<>();

    public User() {
        super();
        this.secret = randomNanoId(8);
        this.enabled = false;
    }

    public String randomNanoId(int size){
        return NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, size);
    }

}

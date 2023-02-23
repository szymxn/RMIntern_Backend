package at.spengergasse.rmbackend.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Getter
@Setter
public class UserLocation  extends AbstractPersistable<Long> {
    private String country;
    private boolean enabled;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public UserLocation(){
        super();
        enabled=false;
    }

    public UserLocation(String country, User user){
        super();
        this.country=country;
        this.user=user;
        enabled=false;
    }
}

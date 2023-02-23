package at.spengergasse.rmbackend.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Privilege extends AbstractPersistable<Long> {
    private String name;
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege(){
        super();
    }

    public Privilege(final String name){
        super();
        this.name=name;
    }
}

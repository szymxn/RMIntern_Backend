package at.spengergasse.rmbackend.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Role extends AbstractPersistable<Long> {
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    private String name;

    public Role(){
        super();
    }

    public Role(final String name){
        super();
        this.name=name;
    }
}

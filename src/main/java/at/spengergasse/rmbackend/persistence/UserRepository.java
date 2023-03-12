package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}

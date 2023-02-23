package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.Domain.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation,Long> {
    UserLocation findByCountryAndUser(String country, User user);
}

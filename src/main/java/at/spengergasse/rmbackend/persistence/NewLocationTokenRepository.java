package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.NewLocationToken;
import at.spengergasse.rmbackend.Domain.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewLocationTokenRepository extends JpaRepository<NewLocationToken,Long> {
    NewLocationToken findByToken(String token);
    NewLocationToken findByUserLocation(UserLocation userLocation);
}

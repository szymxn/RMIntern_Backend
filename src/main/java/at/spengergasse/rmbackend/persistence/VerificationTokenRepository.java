package at.spengergasse.rmbackend.persistence;

import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.Domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import scala.tools.nsc.javac.JavaParsers;

import java.util.Date;
import java.util.stream.Stream;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);

    Stream<VerificationToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from VerificationToken t where t.expiryDate <=?1")
    void deleteAllxpiredSince(Date now);
}

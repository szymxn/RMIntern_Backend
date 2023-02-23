package at.spengergasse.rmbackend.security;

import at.spengergasse.rmbackend.Domain.PasswordResetToken;
import at.spengergasse.rmbackend.persistence.PasswordResetTokenRepository;
import at.spengergasse.rmbackend.persistence.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
@Transactional
public class UserSecurityService implements ISecurityUserService{

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;


    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken= passwordResetTokenRepository.findByToken(token);
        return !isTokenFound(passwordResetToken) ? "invalidToken"
                : isTokenExpired(passwordResetToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passwordResetToken){
        return passwordResetToken!=null;
    }

    private boolean isTokenExpired(PasswordResetToken passwordResetToken){
        Calendar calendar = Calendar.getInstance();
        return passwordResetToken.getExpiryDate().before(calendar.getTime());
    }

}

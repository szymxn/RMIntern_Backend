package at.spengergasse.rmbackend.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);
}

package at.spengergasse.rmbackend.foundation;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade{
    Authentication getAuthentication();
}

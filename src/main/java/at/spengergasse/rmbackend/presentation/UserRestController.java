package at.spengergasse.rmbackend.presentation;

import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.foundation.IAuthenticationFacade;
import at.spengergasse.rmbackend.security.CustomUserDetails;
import at.spengergasse.rmbackend.service.UserService;
import at.spengergasse.rmbackend.service.dto.UserDto;
import at.spengergasse.rmbackend.service.dto.command.LoginDto;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(UserRestController.BASE_URL)
public class UserRestController {
    public static final String BASE_URL ="/api/user";
    public static final String PATH_REGISTER ="/register";
    public static final String PATH_LOGIN="/login";
    public static final String PATH_LOGGED_IN = "/loggedIn";
    public static final String PATH_VAR_TOKEN ="/{token}";
    public static final String PATH_VAR_ID ="/{id}";

    public static final String PATH_UPDATE ="/update";
    public static final String PATH_ALL ="/all";
    private final UserService userService;
    private final IAuthenticationFacade authenticationFacade;
    @Resource(name="authenticationManager")
    private final AuthenticationManager authManager;

    @PostMapping({"",PATH_REGISTER})
    public HttpEntity<User> registerUser(@Valid @RequestBody UserDto userDto) throws Exception {
        User registerdUser = userService.registerNewUserAccount(userDto);
        return ResponseEntity.ok(registerdUser);
    }


    @GetMapping(PATH_VAR_TOKEN)
    public HttpEntity<User> getUserByToken(@PathVariable String token){
        User updatedUser = userService.getUser(token);
        return ResponseEntity.ok(updatedUser);
    }

    /* this method and getUserByToken have the same mapping and that causes an exception
    @GetMapping(PATH_VAR_ID)
    public HttpEntity<Optional<User>> getUserById(@PathVariable Long id){
        Optional<User> updatedUser = userService.getUserByID(id);
        return ResponseEntity.ok(updatedUser);
    }
    */

    @PostMapping(PATH_LOGIN)
    public HttpEntity<Authentication> login(@Valid @RequestBody LoginDto loginDto, final HttpServletRequest request){
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        Authentication auth = authManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return ResponseEntity.ok(auth);
    }

    @GetMapping(PATH_LOGGED_IN)
    public HttpEntity<User> getLoggedInUser(){
        return ResponseEntity.ok(((CustomUserDetails)authenticationFacade.getAuthentication().getDetails()).getUser());
    }
}

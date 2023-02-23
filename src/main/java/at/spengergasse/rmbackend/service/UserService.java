package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.Domain.*;
import at.spengergasse.rmbackend.persistence.*;
import at.spengergasse.rmbackend.service.dto.UserDto;
import at.spengergasse.rmbackend.service.dto.command.MutateUserCommand;
import clojure.lang.Compiler;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.actors.threadpool.Arrays;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService implements IUserService{
    private final ReservierungsRepository reservierungsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Autowired
    private NewLocationTokenRepository newLocationTokenRepository;

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws Exception {
         if(emailExists(accountDto.getEmail())){
             throw new Exception("There is already an account with that email address" +accountDto.getEmail());
         }

         User user = new User();
         user.setVorname(accountDto.getFirstName());
         user.setNachname(accountDto.getLastName());
         user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
         user.setEmail(accountDto.getEmail());
         user.setUsing2FA(accountDto.isUsing2FA());
         user.setRoles(Arrays.asList(new String[]{"ADMIN"}));
         return userRepository.save(user);


    }

    @Override
    public User getUser(String verificationToken) {
        VerificationToken verifi = verificationTokenRepository.findByToken(verificationToken);
        if(verifi!=null){
            return verifi.getUser();
        }

        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {
       userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        VerificationToken verificationToken= verificationTokenRepository.findByUser(user);

        if(verificationToken!=null){
            verificationTokenRepository.delete(verificationToken);
        }

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByUser(user);

        if(passwordResetToken!=null){
            passwordResetTokenRepository.delete(passwordResetToken);
        }

        userRepository.delete(user);
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken myToken = new VerificationToken(token,user);
        verificationTokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return null;
    }

    @Override
    public VerificationToken generateNewVerificationToken(String token) {
        VerificationToken v = verificationTokenRepository.findByToken(token);
        v.updateToken(UUID.randomUUID().toString());

        v= verificationTokenRepository.save(v);

        return v;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
       PasswordResetToken passwordResetToken = new PasswordResetToken(token,user);
       passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public Optional<User> getUserByID(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void changeUserPassword(User user, String password) {
      user.setPassword(passwordEncoder.encode(password));
      userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String password) {
        return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken!=null){

        }

        User user = verificationToken.getUser();
        Calendar calendar= Calendar.getInstance();
        if((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <=0){
            return "TOKEN_EXPIRED";
        }

        user.setEnabled(true);
        userRepository.save(user);

        return "TOKEN_VALID";
    }

    @Override
    public String generateQRUrl(User user) throws UnsupportedEncodingException {
        return null;
    }

    @Override
    public User updateUser2FA(boolean use2FA) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User current = (User) authentication.getPrincipal();
        current.setUsing2FA(use2FA);
        current = userRepository.save(current);
        Authentication auth = new UsernamePasswordAuthenticationToken(current, current.getPassword(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return current;
    }

    @Override
    public List<String> getUsersFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals()
                .stream()
                .filter((u) -> !sessionRegistry.getAllSessions(u,false)
                        .isEmpty())
                .map(o -> {
                    if(o instanceof  User){
                        return ((User)o).getEmail();
                    }
                    else{
                        return o.toString();
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public NewLocationToken isNewLoginLocation(String username, String ip) {
        return null;
    }

    @Override
    public String isValidNewLocationToken(String token) {
        NewLocationToken locationToken = newLocationTokenRepository.findByToken(token);
        if(locationToken==null){
            return null;
        }

        UserLocation userLoc = locationToken.getUserLocation();
        userLoc.setEnabled(true);
        userLoc = userLocationRepository.save(userLoc);
        newLocationTokenRepository.delete(locationToken);
        return userLoc.getCountry();
    }

    @Override
    public void addUserLocation(User user, String ip) {

    }

    private boolean emailExists(String email){
        return userRepository.findByEmail(email) !=null;
    }
}



package at.spengergasse.rmbackend.service.dto.command;

import at.spengergasse.rmbackend.validation.PasswordMatches;
import at.spengergasse.rmbackend.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateUserCommand {
   private String vorname;
   private String nachname;
   private String email;
   private String username;
   private String password;



}

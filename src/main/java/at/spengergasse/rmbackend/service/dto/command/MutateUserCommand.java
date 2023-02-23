package at.spengergasse.rmbackend.service.dto.command;

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

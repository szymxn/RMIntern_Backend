package at.spengergasse.rmbackend.service.dto;

import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.validation.PasswordMatches;
import at.spengergasse.rmbackend.validation.ValidEmail;
import at.spengergasse.rmbackend.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
@Getter
@Setter
public class UserDto {
    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private boolean isUsing2FA;

    public UserDto(String firstName, String lastName, String password, String email, boolean isUsing2FA) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.isUsing2FA = isUsing2FA;
    }
}

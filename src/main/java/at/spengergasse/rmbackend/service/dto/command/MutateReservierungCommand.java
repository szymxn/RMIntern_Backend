package at.spengergasse.rmbackend.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateReservierungCommand {
    private String name;

    private String terminlaenge;
    private MutateUserCommand user;

    private List<MutateReservierungsattributCommand> commands;

    private List<MutateTerminZeitCommand> terminzeit;


}

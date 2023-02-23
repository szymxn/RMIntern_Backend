package at.spengergasse.rmbackend.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateTerminZeitCommand {
    private String TerminZeitStart;
    private String TerminZeitEnde;
    private String Wochentag;
    private MutateReservierungCommand reservierungCommand;
}

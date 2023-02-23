package at.spengergasse.rmbackend.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateKundenZuReservierungCommand {
    private MutateTerminCommand terminCommand;
    private MutateUserCommand userCommand;
    private List<MutateAttributEintragCommand> attributEintragCommandList;
}

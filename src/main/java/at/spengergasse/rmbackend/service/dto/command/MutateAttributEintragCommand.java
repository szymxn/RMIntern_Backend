package at.spengergasse.rmbackend.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateAttributEintragCommand {
    private String eingetragenerWert;
    private MutateReservierungsattributCommand reservierungsattributCommand;
    private MutateKundenZuReservierungCommand kundenZuReservierungCommand;
}

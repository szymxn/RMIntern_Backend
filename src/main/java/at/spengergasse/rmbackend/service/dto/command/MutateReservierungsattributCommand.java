package at.spengergasse.rmbackend.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateReservierungsattributCommand {
    private String Bezeichnung;
    private String Datentyp;
    private String Zeichenlaenge;
    private MutateReservierungCommand reservierungCommand;
    private List<MutateAttributEintragCommand> attributEintragCommandList;
}

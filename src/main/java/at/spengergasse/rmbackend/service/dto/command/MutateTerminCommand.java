package at.spengergasse.rmbackend.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateTerminCommand {
    private LocalDate datum;
    private String ZeitVon;
    private String ZeitBis;
    private MutateReservierungCommand reservierung;
}

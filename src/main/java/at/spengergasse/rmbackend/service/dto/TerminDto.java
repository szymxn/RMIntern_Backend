package at.spengergasse.rmbackend.service.dto;

import at.spengergasse.rmbackend.Domain.Termin;

import java.time.LocalDate;
import java.util.List;

public record TerminDto(LocalDate datum, String ZeitVon, String ZeitBis, String token,ReserveriungDto reserveriung) {
    public TerminDto(Termin termin){
        this(termin.getDatum(),termin.getZeitVon(),termin.getZeitBis(), termin.getToken(), new ReserveriungDto(termin.getReservierung()));
    }
}

package at.spengergasse.rmbackend.service.dto;

import at.spengergasse.rmbackend.Domain.KundenZuReservierung;

import java.util.List;

public record KundenZuReservierungsDto(TerminDto termin, List<AttributEintragDto> attributEintrags) {
    public KundenZuReservierungsDto(KundenZuReservierung kundenZuReservierung){
        this(new TerminDto(kundenZuReservierung.getTermin()),
                kundenZuReservierung.getAttributEintragList().stream().map(AttributEintragDto::new).toList());
    }
}

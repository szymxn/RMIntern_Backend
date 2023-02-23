package at.spengergasse.rmbackend.service.dto;

import at.spengergasse.rmbackend.Domain.AttributEintrag;

public record AttributEintragDto(String eingetragenerWert, String token, ReservierungsattributDto reservierungsattribut, KundenZuReservierungsDto kundenZuReservierung) {
    public AttributEintragDto(AttributEintrag attributEintrag){
        this(attributEintrag.getEingetragenerWert(),attributEintrag.getToken(), new ReservierungsattributDto(attributEintrag.getReservierungsattribut()), new KundenZuReservierungsDto(attributEintrag.getKundenzureservierung()));
    }
}

package at.spengergasse.rmbackend.service.dto;

import at.spengergasse.rmbackend.Domain.Reservierungsattribut;

import java.util.List;

public record ReservierungsattributDto(String Bezeichnung, String Datentyp, String Zeichenlaenge,String token, ReserveriungDto reserveriung, List<AttributEintragDto> attributEintragList) {
    public ReservierungsattributDto(Reservierungsattribut reservierungsattribut){
        this(reservierungsattribut.getBezeichnung(),reservierungsattribut.getDatentyp(),reservierungsattribut.getZeichenlaenge(),
                reservierungsattribut.getToken(),
                new ReserveriungDto(reservierungsattribut.getReservierung()),
                reservierungsattribut.getAttributEintragList().stream().map(AttributEintragDto::new).toList());
    }
}

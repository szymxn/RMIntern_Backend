package at.spengergasse.rmbackend.service.dto;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.Termin;

import java.util.List;

public record ReserveriungDto(String name, String Terminlaenge, String token, UserDto user, List<ReservierungsattributDto> res, List<TerminZeitDto> terminZeit) {
    public ReserveriungDto(Reservierung reservierung) {
        this(reservierung.getName(),reservierung.getTerminlaenge(), reservierung.getToken(),
                new UserDto(reservierung.getUser().getVorname(),reservierung.getUser().getNachname(),reservierung.getUser().getPassword(),
                        reservierung.getUser().getEmail(),reservierung.getUser().isUsing2FA()),
                reservierung.getReservierungsattributs().stream().map(ReservierungsattributDto::new).toList(),
                reservierung.getTerminZeitList().stream().map(TerminZeitDto::new).toList());
    }
}

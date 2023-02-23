package at.spengergasse.rmbackend.service.dto;

import at.spengergasse.rmbackend.Domain.TerminZeit;

public record TerminZeitDto(String TerminZeitStart, String TerminZeitEnde, String Wochentag, String token, ReserveriungDto reserveriung ) {
    public TerminZeitDto(TerminZeit terminZeit){
        this(terminZeit.getTerminZeitStart(),terminZeit.getTerminZeitEnde(),terminZeit.getWochentag(), terminZeit.getToken(), new ReserveriungDto(terminZeit.getReservierung()));
    }
}

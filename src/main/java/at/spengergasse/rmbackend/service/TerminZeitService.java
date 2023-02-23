package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.Domain.*;
import at.spengergasse.rmbackend.persistence.TerminZeitRepository;
import at.spengergasse.rmbackend.service.dto.command.MutateTerminZeitCommand;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TerminZeitService {
    private final TerminZeitRepository terminZeitRepository;
    private final TokenService tokenService;

    public List<TerminZeit> getTermins() {
        return terminZeitRepository.findAll();
    }


    public Optional<TerminZeit> getTermin(Long id){
        return terminZeitRepository.findById(id);
    }

    public TerminZeit createTerminZeit(MutateTerminZeitCommand command) {
        Objects.requireNonNull(command.getWochentag(), "Wochentag cannot be null");
        Objects.requireNonNull(command.getTerminZeitStart(), "TerminZeitStart cannot be null");
        Objects.requireNonNull(command.getTerminZeitEnde(), "TerminZeitEnde cannot be null");
        Objects.requireNonNull(command.getReservierungCommand(), "Reservierung must consist");

        if (command.getWochentag().isEmpty() || command.getWochentag().isBlank()) {
            throw new IllegalArgumentException("Wochentag cannot be empty or blank");
        }
        if (command.getTerminZeitStart().isEmpty() || command.getTerminZeitStart().isBlank()) {
            throw new IllegalArgumentException("TerminZeitStart cannot be empty or blank");
        }
        if (command.getTerminZeitEnde().isEmpty() || command.getTerminZeitEnde().isBlank()) {
            throw new IllegalArgumentException("TerminZeitEnde cannot be empty or blank");
        }

        try {

                return terminZeitRepository.save(TerminZeit.builder()
                        .TerminZeitStart(command.getTerminZeitStart())
                        .TerminZeitEnde(command.getTerminZeitEnde())
                        .Wochentag(command.getWochentag())
                        .reservierung(Reservierung.builder()
                                .name(command.getReservierungCommand().getName())
                                .terminlaenge(command.getReservierungCommand().getTerminlaenge())
                                .user(User.builder()
                                        .vorname(command.getReservierungCommand().getUser().getVorname())
                                        .nachname(command.getReservierungCommand().getUser().getNachname())
                                        .email(command.getReservierungCommand().getUser().getEmail())
                                        .username(command.getReservierungCommand().getUser().getUsername())
                                        .password(command.getReservierungCommand().getUser().getPassword())
                                        .secret(tokenService.createNanoId())
                                        .build())
                                .build())
                                .token(tokenService.createNanoId())
                        .build());


        } catch (PersistenceException e) {
            throw new PersistenceException("Couldnt create TerminZeit " + e);
        }
    }

    public TerminZeit update(Long id,MutateTerminZeitCommand command){
        Optional<TerminZeit> find = terminZeitRepository.findByReservierungId(id);
        if(find.isPresent()){
            TerminZeit x = find.get();
            if(x.getWochentag()!=null) x.setWochentag(command.getWochentag());
            if(x.getTerminZeitStart()!=null) x.setTerminZeitStart(command.getTerminZeitStart());
            if(x.getTerminZeitEnde()!=null) x.setTerminZeitEnde(command.getTerminZeitEnde());

            terminZeitRepository.save(x);
        }
        return find.get();
    }

    public void deleteTerminZeits(){
        terminZeitRepository.deleteAll();
        log.info("All TerminZeits has been deleted" +terminZeitRepository.count());
    }

    public TerminZeit deleteTerminZeit(Long id){
        TerminZeit t =null;
        Optional<TerminZeit> find = terminZeitRepository.findByReservierungId(id);
        if(find.isPresent()){
            t= find.get();
            terminZeitRepository.delete(t);
            log.info("TerminZeit has been deleted from the Reservation with Id " +t.getReservierung().getId());
        }
        return t;
    }
}

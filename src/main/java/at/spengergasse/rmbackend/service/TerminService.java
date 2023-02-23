package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.Domain.*;
import at.spengergasse.rmbackend.persistence.TerminRepository;
import at.spengergasse.rmbackend.persistence.TerminZeitRepository;
import at.spengergasse.rmbackend.service.dto.command.MutateTerminCommand;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TerminService {
    private final TerminZeitRepository terminZeitRepository;
    private final TerminRepository terminRepository;
    private final TokenService tokenService;

    public List<Termin> getTermins(){
        return terminRepository.findAll();
    }

    public Optional<Termin> getTerminByDatum(LocalDate datum){
       return terminRepository.findByDatum(datum);
    }

    public Optional<Termin> getTermin(Long id){
        return terminRepository.findById(id);
    }

    public Termin createTermin(MutateTerminCommand terminCommand){
        Optional<Termin> findTermin = terminRepository.findByDatum(terminCommand.getDatum());
        Objects.requireNonNull(terminCommand.getDatum(), "Date cannot be null");
        Objects.requireNonNull(terminCommand.getZeitVon(), "ZeitVon cannot be null");
        Objects.requireNonNull(terminCommand.getZeitBis(), "ZeitBis cannot be null");

        if(terminCommand.getDatum().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date cannot be in the future");
        }
        if(terminCommand.getZeitVon().isBlank() || terminCommand.getZeitVon().isEmpty()){
            throw new IllegalArgumentException("ZeitVon cannot be empty or blank");
        }
        if(terminCommand.getZeitBis().isEmpty() || terminCommand.getZeitBis().isEmpty()){
            throw new IllegalArgumentException("ZeitBis cannot be empty or blank");
        }

        try{
            if(findTermin.isPresent()){
                throw new IllegalArgumentException("Already exist");
            }

            else{
                return terminRepository.save(Termin.builder()
                                .datum(terminCommand.getDatum())
                                .ZeitVon(terminCommand.getZeitVon())
                                .ZeitBis(terminCommand.getZeitBis())
                                .reservierung(Reservierung.builder()
                                        .name(terminCommand.getReservierung().getName())
                                        .terminlaenge(terminCommand.getReservierung().getTerminlaenge())
                                        .user(User.builder()
                                                .vorname(terminCommand.getReservierung().getUser().getVorname())
                                                .nachname(terminCommand.getReservierung().getUser().getNachname())
                                                .email(terminCommand.getReservierung().getUser().getEmail())
                                                .username(terminCommand.getReservierung().getUser().getUsername())
                                                .password(terminCommand.getReservierung().getUser().getPassword())
                                                .secret(tokenService.createNanoId())
                                                .build())
                                        .token(tokenService.createNanoId())
                                        .build())
                                .token(tokenService.createNanoId())
                        .build());
            }

        }catch (PersistenceException e){
            throw new PersistenceException("Termin couldn't be created "+e);
        }
    }

    public Termin updateTermins(Long id, MutateTerminCommand command){
        Optional<Termin> find = terminRepository.findByReservierungId(id);
        if(find.isPresent()){
            Termin x = find.get();
            if(x.getDatum() !=null && x.getDatum().isBefore(LocalDate.now())) x.setDatum(command.getDatum());
            if(x.getZeitVon()!=null) x.setZeitVon(command.getZeitVon());
            if(x.getZeitBis()!=null) x.setZeitBis(command.getZeitBis());

            terminRepository.save(x);
        }

        return find.get();
    }

    public Termin deleteTermin(Long id){
        Termin termin =null;
        Optional<Termin> find = terminRepository.findByReservierungId(id);
        if(find.isPresent()){
            termin = find.get();
            terminRepository.delete(termin);
            log.info("Termin has been successfully deleted from the Reservation with Id " +termin.getReservierung().getId());
        }
        return termin;
    }

    public void deleteTermins(){
        terminRepository.deleteAll();
        log.info("All Termins has been deleted " + "Anzahl = " +terminRepository.count());
    }
}

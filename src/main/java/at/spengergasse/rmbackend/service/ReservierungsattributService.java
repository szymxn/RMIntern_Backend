package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.Domain.*;
import at.spengergasse.rmbackend.persistence.ReservierungsRepository;
import at.spengergasse.rmbackend.persistence.ReservierungsattributRepository;
import at.spengergasse.rmbackend.service.dto.command.MutateReservierungsattributCommand;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ReservierungsattributService {
    private final ReservierungsattributRepository repository;
    private final TokenService tokenService;
    private final ReservierungsRepository reservierungsRepository;

    public List<Reservierungsattribut> getReservierungsattributs(){
        return repository.findAll();
    }
    public Optional<Reservierungsattribut> getReservierungsattribut(Long id){
        return repository.findById(id);
    }




    public Reservierungsattribut createReservierungsattribut(MutateReservierungsattributCommand command){
        Objects.requireNonNull(command.getBezeichnung(), "Bezeichnung cannot be null");
        Objects.requireNonNull(command.getDatentyp(), "Datentyp cannot be null");
        Objects.requireNonNull(command.getZeichenlaenge(), "Zeichenlaenge cannot be null");
        Objects.requireNonNull(command.getReservierungCommand(), "Reservierung cannot be null");

        if(command.getBezeichnung().isEmpty() || command.getBezeichnung().isBlank()){
            throw new IllegalArgumentException("Bezeichnung cannot be empty or blank");
        }

        if(command.getDatentyp().isEmpty() || command.getDatentyp().isBlank()){
            throw new IllegalArgumentException("Datentyp cannot be empty or blank");
        }

        if(command.getZeichenlaenge().isEmpty() || command.getZeichenlaenge().isBlank()){
            throw new IllegalArgumentException("Zeichenlaenge cannot be empty or blank");
        }

        try{
                    return repository.save(Reservierungsattribut.builder()
                            .Bezeichnung(command.getBezeichnung())
                            .Datentyp(command.getDatentyp())
                            .Zeichenlaenge(command.getZeichenlaenge())
                            .reservierung(Reservierung.builder()
                                    .name(command.getReservierungCommand().getName())
                                    .terminlaenge(command.getReservierungCommand().getTerminlaenge())
                                    .user(User.builder()
                                            .vorname(command.getReservierungCommand().getUser().getVorname())
                                            .nachname(command.getReservierungCommand().getUser().getNachname())
                                            .email(command.getReservierungCommand().getUser().getEmail())
                                            .username(command.getReservierungCommand().getUser().getEmail())
                                            .password(command.getReservierungCommand().getUser().getPassword())
                                            .secret(tokenService.createNanoId())
                                            .build())
                                    .token(tokenService.createNanoId())
                                    .build())
                            .token(tokenService.createNanoId())
                            .build());




        }catch (PersistenceException e){
            throw new PersistenceException("Couldnt create a Reservierungsattribut");
        }

    }

    public Reservierungsattribut updateAttributs(Long id, MutateReservierungsattributCommand command){
        Optional<Reservierungsattribut> find= repository.findByReservierungId(id);
        if(find.isPresent()){
            Reservierungsattribut x = find.get();
            if(x.getBezeichnung() !=null) x.setBezeichnung(command.getBezeichnung());
            if(x.getDatentyp()!=null) x.setDatentyp(command.getDatentyp());
            if(x.getZeichenlaenge()!=null) x.setZeichenlaenge(command.getZeichenlaenge());

            repository.save(x);
        }

        return find.get();

    }

    public Reservierungsattribut deleteReservierungsAttributById(Long id){
        Reservierungsattribut t = null;
        Optional<Reservierungsattribut> find = repository.findByReservierungId(id);
        if(find.isPresent()){
            t = find.get();
            repository.delete(t);
            log.info("Reservierungsattribut has been deleted from Reservation with the Id" +t.getReservierung().getId());
        }

        return t;
    }

    public void deleteReservierungsattributs(){
        reservierungsRepository.deleteAll();
        log.info("All Reservierungsattributs has beenn deleted  " +reservierungsRepository.count());
    }
}

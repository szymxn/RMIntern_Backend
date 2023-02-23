package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.Domain.*;
import at.spengergasse.rmbackend.persistence.ReservierungsRepository;
import at.spengergasse.rmbackend.persistence.UserRepository;
import at.spengergasse.rmbackend.service.dto.command.MutateReservierungCommand;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReservierungService {
    private final ReservierungsRepository reservierungsRepository;
    private final TokenService tokenService;

    public List<Reservierung> getReservierung() {
        return reservierungsRepository.findAll();
    }

    public Optional<Reservierung> getRev(String name) {
        return reservierungsRepository.findByName(name);
    }

    public Optional<Reservierung> getRevById(Long id) {
        return reservierungsRepository.findById(id);
    }

    public Reservierung createReservierung(MutateReservierungCommand command) {
        User user = null;
        Optional<Reservierung> reservierung1 = reservierungsRepository.findByName(command.getName());
        Objects.requireNonNull(command, "Command must not be null");
        Objects.requireNonNull(command.getUser(), "User must consist");
        if (command.getName() == null || command.getName().isEmpty() || command.getName().isBlank()) {
            throw new IllegalArgumentException("Name must not be null or empty or blank");
        }

        if (command.getTerminlaenge() == null || command.getTerminlaenge().isEmpty() || command.getTerminlaenge().isBlank()) {
            throw new IllegalArgumentException("Terminlaenge must not be null or empty or blank");
        }

        try {

            if (reservierung1.isPresent()) {
                throw new IllegalArgumentException("Reservierung already exist");
            }
            else {
                return reservierungsRepository.save(Reservierung.builder()
                        .name(command.getName())
                        .terminlaenge(command.getTerminlaenge())
                       // .token(tokenService.createNanoId())
                                .terminZeitList(command.getTerminzeit().stream().map(r -> TerminZeit.builder()
                                                .Wochentag(r.getWochentag())
                                                .TerminZeitStart(r.getTerminZeitStart())
                                                .TerminZeitEnde(r.getTerminZeitEnde())
                                                .build()).toList()).build());
            }
        } catch (PersistenceException e) {
           throw new IllegalArgumentException("Couldn't create");
        }

    }

    public void deleteReservations(){
        reservierungsRepository.deleteAll();
        log.info("All Reservations has been deleted " + "Anzahl = " +reservierungsRepository.count());
    }

    public Reservierung deleteReservation(Long user_id){
        Reservierung res = null;
        Optional<Reservierung> reservierung = reservierungsRepository.findByUser_Id(user_id);
        try{
            if(reservierung.isPresent()){
                 res = reservierung.get();
                if(res.isBlocked()){
                    reservierungsRepository.delete(res);
                    return res;
                }
            }

        }catch (PersistenceException e){
            throw new PersistenceException("Couldn't delete Reservierung due to non existence");
        }

        return res;
    }

    public Reservierung update(Long user_id, MutateReservierungCommand command){
        Optional<Reservierung> find = reservierungsRepository.findByUser_Id(user_id);
        if(find.isPresent()){
            Reservierung x = find.get();
            if(x.getName()!=null) x.setName(command.getName());
            if(x.getTerminlaenge()!=null)x.setTerminlaenge(command.getTerminlaenge());

            reservierungsRepository.save(x);
        }
        return find.get();
    }

}

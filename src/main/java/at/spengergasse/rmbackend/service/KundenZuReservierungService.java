package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.Domain.KundenZuReservierung;
import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.Termin;
import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.persistence.KundenZuReservierungRepository;
import at.spengergasse.rmbackend.persistence.TerminZeitRepository;
import at.spengergasse.rmbackend.service.dto.command.MutateKundenZuReservierungCommand;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import scala.tools.cmd.gen.AnyValReps;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class KundenZuReservierungService {
    private final TerminZeitRepository terminZeitRepository;
    private final KundenZuReservierungRepository kundenZuReservierungRepository;
    private final TokenService tokenService;

    public List<KundenZuReservierung> getAll(){
        return kundenZuReservierungRepository.findAll();
    }

    public Optional<KundenZuReservierung> getKRById(Long id){
        return kundenZuReservierungRepository.findById(id);
    }

    public KundenZuReservierung createKZuR(MutateKundenZuReservierungCommand command){
        Optional<KundenZuReservierung> find = kundenZuReservierungRepository.findKundenZuReservierungByUser_Email(command.getUserCommand().getEmail());
        Objects.requireNonNull(command, "Command must not be null");
        Objects.requireNonNull(command.getTerminCommand(), "Termin must exist");
        Objects.requireNonNull(command.getUserCommand(), "User must exist");

        try{
            if(find.isPresent()){
                throw new IllegalArgumentException("it already exist");
            }

            else{
                return kundenZuReservierungRepository.save(KundenZuReservierung.builder()
                                .user(User.builder()
                                        .vorname(command.getUserCommand().getVorname())
                                        .nachname(command.getUserCommand().getNachname())
                                        .email(command.getUserCommand().getEmail())
                                        .username(command.getUserCommand().getEmail())
                                        .password(command.getUserCommand().getPassword())
                                        .enabled(false)
                                        .isUsing2FA(false)
                                        .secret(tokenService.createNanoId())
                                        .build())
                                .termin(Termin.builder()
                                        .ZeitVon(command.getTerminCommand().getZeitVon())
                                        .ZeitBis(command.getTerminCommand().getZeitBis())
                                        .datum(command.getTerminCommand().getDatum())
                                        .reservierung(Reservierung.builder()
                                                .isBlocked(false)
                                                .name(command.getTerminCommand().getReservierung().getName())
                                                .terminlaenge(command.getTerminCommand().getReservierung().getTerminlaenge())
                                                .user(User.builder()
                                                        .vorname(command.getTerminCommand().getReservierung().getUser().getVorname())
                                                        .nachname(command.getTerminCommand().getReservierung().getUser().getNachname())
                                                        .email(command.getTerminCommand().getReservierung().getUser().getEmail())
                                                        .username(command.getTerminCommand().getReservierung().getUser().getUsername())
                                                        .password(command.getTerminCommand().getReservierung().getUser().getPassword())
                                                        .enabled(false)
                                                        .isUsing2FA(false)
                                                        .secret(tokenService.createNanoId())
                                                        .build())
                                                .token(tokenService.createNanoId())
                                                .build())
                                        .token(tokenService.createNanoId())
                                        .build())
                                .token(tokenService.createNanoId())
                        .build());
            }

        }catch (PersistenceException e){
            throw new IllegalArgumentException("Couldn't create");
        }
    }

    public void deleteKzuRs(){
        kundenZuReservierungRepository.deleteAll();
        log.info("All KzuRs has been deleted " + "Anzahl = " +kundenZuReservierungRepository.count());
    }

    public KundenZuReservierung deleteKzuR(Long user_id, Long termin_id) {
        KundenZuReservierung k = null;
        Optional<KundenZuReservierung> find = kundenZuReservierungRepository.findByUser_IdAndAndTermin_Id(user_id,termin_id);
        try{
            if(find.isPresent()){
                k = find.get();
                kundenZuReservierungRepository.delete(k);
            }

        }catch (PersistenceException e){
            throw new PersistenceException("Couldn delete KzuR");
        }
        return k;
    }

    public KundenZuReservierung update(Long user_id, Long termin_id, MutateKundenZuReservierungCommand command){
        Optional<KundenZuReservierung> find = kundenZuReservierungRepository.findByUser_IdAndAndTermin_Id(user_id,termin_id);
        if(find.isPresent()){
            KundenZuReservierung x = find.get();
            if(x.getUser().getVorname()!=null) x.getUser().setVorname(command.getUserCommand().getVorname());

            kundenZuReservierungRepository.save(x);
        }

        return find.get();
    }
}

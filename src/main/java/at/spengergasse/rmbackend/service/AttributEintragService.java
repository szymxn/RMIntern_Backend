package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.Domain.*;
import at.spengergasse.rmbackend.persistence.AttributEintragRepository;
import at.spengergasse.rmbackend.service.dto.command.MutateAttributEintragCommand;
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
public class AttributEintragService {
   private final AttributEintragRepository attributEintragRepository;
   private final TokenService tokenService;

   public List<AttributEintrag> getAttributEintrag(){
       return attributEintragRepository.findAll();
   }

   public Optional<AttributEintrag> getAttById(Long id){
       return attributEintragRepository.findById(id);
   }

   public AttributEintrag createAttributEintrag(MutateAttributEintragCommand command){
       Optional<AttributEintrag> att = attributEintragRepository.findAttributEintragByEingetragenerWert(command.getEingetragenerWert());
       Objects.requireNonNull(command, "Command must not be null");
       Objects.requireNonNull(command.getEingetragenerWert(), "Registered value cannot be null");
       Objects.requireNonNull(command.getReservierungsattributCommand(), "Reservierungsattribut cannot be null");
       Objects.requireNonNull(command.getKundenZuReservierungCommand(), "Cannot be null");

       try{
           if(att.isPresent()){
               throw new IllegalArgumentException("AttribuEintrag already exist");
           }

           else{

               return attributEintragRepository.save(AttributEintrag.builder()
                               .eingetragenerWert(command.getEingetragenerWert())
                               .reservierungsattribut(Reservierungsattribut.builder()
                                       .Datentyp(command.getReservierungsattributCommand().getDatentyp())
                                       .Bezeichnung(command.getReservierungsattributCommand().getBezeichnung())
                                       .Zeichenlaenge(command.getReservierungsattributCommand().getZeichenlaenge())
                                       .token(tokenService.createNanoId())
                                       .build())
                               .kundenzureservierung(KundenZuReservierung.builder()
                                       .user(User.builder()
                                               .vorname(command.getKundenZuReservierungCommand().getUserCommand().getVorname())
                                               .nachname(command.getKundenZuReservierungCommand().getUserCommand().getNachname())
                                               .email(command.getKundenZuReservierungCommand().getUserCommand().getEmail())
                                               .username(command.getKundenZuReservierungCommand().getUserCommand().getUsername())
                                               .password(command.getKundenZuReservierungCommand().getUserCommand().getPassword())
                                               .enabled(false)
                                               .isUsing2FA(false)
                                               .secret(tokenService.createNanoId())
                                               .build())
                                       .termin(Termin.builder()
                                               .ZeitVon(command.getKundenZuReservierungCommand().getTerminCommand().getZeitVon())
                                               .ZeitBis(command.getKundenZuReservierungCommand().getTerminCommand().getZeitBis())
                                               .datum(command.getKundenZuReservierungCommand().getTerminCommand().getDatum())
                                               .reservierung(Reservierung.builder()
                                                       .isBlocked(false)
                                                       .name(command.getKundenZuReservierungCommand().getTerminCommand().getReservierung().getName())
                                                       .terminlaenge(command.getKundenZuReservierungCommand().getTerminCommand().getReservierung().getTerminlaenge())
                                                       .user(User.builder()
                                                               .vorname(command.getKundenZuReservierungCommand().getTerminCommand().getReservierung().getUser().getVorname())
                                                               .nachname(command.getKundenZuReservierungCommand().getTerminCommand().getReservierung().getUser().getNachname())
                                                               .email(command.getKundenZuReservierungCommand().getTerminCommand().getReservierung().getUser().getEmail())
                                                               .username(command.getKundenZuReservierungCommand().getTerminCommand().getReservierung().getUser().getUsername())
                                                               .password(command.getKundenZuReservierungCommand().getTerminCommand().getReservierung().getUser().getPassword())
                                                               .enabled(false)
                                                               .isUsing2FA(false)
                                                               .secret(tokenService.createNanoId())
                                                               .build())
                                                       .token(tokenService.createNanoId())
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

   public void deleteAttributEintrags(){
       attributEintragRepository.deleteAll();
       log.info("All AttributEintraege has been deleted " + "Anzahl = " +attributEintragRepository.count());
   }

   public AttributEintrag deleteAttribuEintrag(Long att_id, Long kr_id){
       AttributEintrag a = null;
       Optional<AttributEintrag> attributEintrag = attributEintragRepository.findAttributEintragByReservierungsattributIdAndAndKundenzureservierungId(att_id,kr_id);
       try{
           if(attributEintrag.isPresent()){
               a = attributEintrag.get();
               attributEintragRepository.delete(a);
           }
       }catch (PersistenceException e){
           throw new PersistenceException(" Couldn create AttribuEintrag due to non existence");
       }
       return a;
   }

   public AttributEintrag update(Long att_id, Long kr_id, MutateAttributEintragCommand command){
       Optional<AttributEintrag> find = attributEintragRepository.findAttributEintragByReservierungsattributIdAndAndKundenzureservierungId(att_id,kr_id);
       if(find.isPresent()){
           AttributEintrag x = find.get();
           if(x.getEingetragenerWert()!=null) x.setEingetragenerWert(command.getEingetragenerWert());
           attributEintragRepository.save(x);
       }
       return find.get();
   }
}
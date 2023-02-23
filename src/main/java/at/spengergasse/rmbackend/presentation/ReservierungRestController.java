package at.spengergasse.rmbackend.presentation;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.User;
import at.spengergasse.rmbackend.persistence.ReservierungsRepository;
import at.spengergasse.rmbackend.service.ReservierungService;
import at.spengergasse.rmbackend.service.UserService;
import at.spengergasse.rmbackend.service.dto.ReserveriungDto;
import at.spengergasse.rmbackend.service.dto.UserDto;
import at.spengergasse.rmbackend.service.dto.command.MutateReservierungCommand;
import at.spengergasse.rmbackend.service.dto.command.MutateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(ReservierungRestController.BASE_URL)
public class ReservierungRestController {
    public static final String BASE_URL ="/api/reservierung";
    public static final String PATH_INDEX ="/";
    public static final String PATH_VAR_ID ="/{id}";
    public static final String ROUTE_ID = BASE_URL + PATH_VAR_ID;

    private final ReservierungService reservierungService;
    private final ReservierungsRepository reservierungsRepository;

    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<ReserveriungDto>> getReservierung(){
        List<Reservierung> reservierungs = reservierungService.getReservierung();

        return(reservierungs.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(reservierungs.stream().map(ReserveriungDto::new).toList());
    }

    @GetMapping({PATH_VAR_ID})
    public HttpEntity<ReserveriungDto> getRevById(@PathVariable Long id){
        return reservierungService.getRevById(id)
                .map(ReserveriungDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping({"",PATH_INDEX})
    public HttpEntity<ReserveriungDto> createReservierung(@RequestBody MutateReservierungCommand command){
        Reservierung reservierung = reservierungService.createReservierung(command);
        return ResponseEntity.ok(new ReserveriungDto(reservierung));
    }


    @PatchMapping({PATH_VAR_ID})
    public HttpEntity<ReserveriungDto> updateReservierung(@PathVariable Long id, @RequestBody MutateReservierungCommand command){
        return ResponseEntity.ok(new ReserveriungDto(reservierungService.update(id,command)));
    }

    @DeleteMapping({"",PATH_INDEX})
    public HttpEntity<Void> deleteReservierungen(){
        reservierungService.deleteReservations();
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping(PATH_VAR_ID)
    public HttpEntity<Void> deleteReservierungById(@PathVariable Long id){
        reservierungService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

}

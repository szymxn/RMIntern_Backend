package at.spengergasse.rmbackend.presentation;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.Reservierungsattribut;
import at.spengergasse.rmbackend.service.ReservierungsattributService;
import at.spengergasse.rmbackend.service.dto.ReserveriungDto;
import at.spengergasse.rmbackend.service.dto.ReservierungsattributDto;
import at.spengergasse.rmbackend.service.dto.command.MutateReservierungCommand;
import at.spengergasse.rmbackend.service.dto.command.MutateReservierungsattributCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReservierungsattributRestController {
    public static final String BASE_URL ="/api/reservierungsattribut";
    public static final String PATH_INDEX ="/";
    public static final String PATH_VAR_ID ="/{id}";
    public static final String ROUTE_ID = BASE_URL + PATH_VAR_ID;

    private final ReservierungsattributService reservierungsattributService;

    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<ReservierungsattributDto>> getReservierungsattributs(){
        List<Reservierungsattribut> reservierungsattributs = reservierungsattributService.getReservierungsattributs();

        return(reservierungsattributs.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(reservierungsattributs.stream().map(ReservierungsattributDto::new).toList());
    }

    @GetMapping({PATH_VAR_ID})
    public HttpEntity<ReservierungsattributDto> getById(@PathVariable Long id){
        return reservierungsattributService.getReservierungsattribut(id)
                .map(ReservierungsattributDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping({"",PATH_INDEX})
    public HttpEntity<ReservierungsattributDto> createReservierungsattribut(@RequestBody MutateReservierungsattributCommand command){
        Reservierungsattribut reservierungsattribut = reservierungsattributService.createReservierungsattribut(command);
        return ResponseEntity.created(createSelfLink(reservierungsattribut)).body(new ReservierungsattributDto(reservierungsattribut));
    }


    private URI createSelfLink(Reservierungsattribut reservierungsattribut){
        URI self = UriComponentsBuilder.fromPath(ROUTE_ID)
                .uriVariables(Map.of("id",reservierungsattribut.getId()))
                .build().toUri();
        return self;
    }

    @PatchMapping({PATH_VAR_ID})
    public HttpEntity<ReservierungsattributDto> update(@PathVariable Long id, @RequestBody MutateReservierungsattributCommand command){
        return ResponseEntity.ok(new ReservierungsattributDto(reservierungsattributService.updateAttributs(id,command)));
    }

    @DeleteMapping({"",PATH_INDEX})
    public HttpEntity<Void> deleteReservierungsattribut(){
        reservierungsattributService.deleteReservierungsattributs();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(PATH_VAR_ID)
    public HttpEntity<Void> deleteReservierungsattribuById(@PathVariable Long id){
        reservierungsattributService.deleteReservierungsAttributById(id);
        return ResponseEntity.ok().build();
    }
}

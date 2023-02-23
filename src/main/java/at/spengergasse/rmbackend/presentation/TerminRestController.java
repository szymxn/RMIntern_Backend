package at.spengergasse.rmbackend.presentation;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.Termin;
import at.spengergasse.rmbackend.service.TerminService;
import at.spengergasse.rmbackend.service.dto.ReserveriungDto;
import at.spengergasse.rmbackend.service.dto.TerminDto;
import at.spengergasse.rmbackend.service.dto.command.MutateReservierungCommand;
import at.spengergasse.rmbackend.service.dto.command.MutateTerminCommand;
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
@RequestMapping(TerminRestController.BASE_URL)
public class TerminRestController {
    public static final String BASE_URL ="/api/termin";
    public static final String PATH_INDEX ="/";
    public static final String PATH_VAR_ID ="/{id}";
    public static final String ROUTE_ID = BASE_URL + PATH_VAR_ID;
    private final TerminService terminService;

    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<TerminDto>> getTermins(){
        List<Termin> termins = terminService.getTermins();

        return(termins.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(termins.stream().map(TerminDto::new).toList());
    }

    @GetMapping({PATH_VAR_ID})
    public HttpEntity<TerminDto> getById(@PathVariable Long id){
        return terminService.getTermin(id)
                .map(TerminDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping({"",PATH_INDEX})
    public HttpEntity<TerminDto> createTermin(@RequestBody MutateTerminCommand command){
        Termin termin = terminService.createTermin(command);
        return ResponseEntity.created(createSelfLink(termin)).body(new TerminDto(termin));
    }

    @PatchMapping({PATH_VAR_ID})
    public HttpEntity<TerminDto> updateTermin(@PathVariable Long id, @RequestBody MutateTerminCommand command){
        return ResponseEntity.ok(new TerminDto(terminService.updateTermins(id,command)));
    }

    private URI createSelfLink(Termin termin){
        URI self = UriComponentsBuilder.fromPath(ROUTE_ID)
                .uriVariables(Map.of("id",termin.getId()))
                .build().toUri();
        return self;
    }

    @DeleteMapping(PATH_VAR_ID)
    public HttpEntity<Void> deleteTermin(@PathVariable Long id){
        terminService.deleteTermin(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"",PATH_INDEX})
    public HttpEntity<Void> deleteTermins(){
        terminService.deleteTermins();
        return ResponseEntity.ok().build();
    }

}

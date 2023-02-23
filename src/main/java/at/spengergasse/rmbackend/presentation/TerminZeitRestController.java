package at.spengergasse.rmbackend.presentation;

import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.Domain.TerminZeit;
import at.spengergasse.rmbackend.service.TerminZeitService;
import at.spengergasse.rmbackend.service.dto.ReserveriungDto;
import at.spengergasse.rmbackend.service.dto.TerminZeitDto;
import at.spengergasse.rmbackend.service.dto.command.MutateReservierungCommand;
import at.spengergasse.rmbackend.service.dto.command.MutateTerminZeitCommand;
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
@RequestMapping(TerminZeitRestController.BASE_URL)
public class TerminZeitRestController {
    public static final String BASE_URL ="/api/terminzeit";
    public static final String PATH_INDEX ="/";
    public static final String PATH_VAR_ID ="/{id}";
    public static final String ROUTE_ID = BASE_URL + PATH_VAR_ID;

    private final TerminZeitService terminZeitService;

    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<TerminZeitDto>> getTerminZeits(){
        List<TerminZeit> terminZeits = terminZeitService.getTermins();

        return(terminZeits.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(terminZeits.stream().map(TerminZeitDto::new).toList());
    }

    @GetMapping({PATH_VAR_ID})
    public HttpEntity<TerminZeitDto> getById(@PathVariable Long id){
        return terminZeitService.getTermin(id)
                .map(TerminZeitDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping({"",PATH_INDEX})
    public HttpEntity<TerminZeitDto> createTerminZeit(@RequestBody MutateTerminZeitCommand command){
        TerminZeit terminZeit = terminZeitService.createTerminZeit(command);
        return ResponseEntity.created(createSelfLink(terminZeit)).body(new TerminZeitDto(terminZeit));
    }

    @PatchMapping({PATH_VAR_ID})
    public HttpEntity<TerminZeitDto> updateTerminzeit(@PathVariable Long id, @RequestBody MutateTerminZeitCommand command){
        return ResponseEntity.ok(new TerminZeitDto(terminZeitService.update(id,command)));
    }

    private URI createSelfLink(TerminZeit terminZeit){
        URI self = UriComponentsBuilder.fromPath(ROUTE_ID)
                .uriVariables(Map.of("id",terminZeit.getId()))
                .build().toUri();
        return self;
    }

    @DeleteMapping(PATH_VAR_ID)
    public HttpEntity<Void> deleteTerminZeitById(@PathVariable Long id){
        terminZeitService.deleteTerminZeit(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"",PATH_INDEX})
    public HttpEntity<Void> deleteTerminzeiten(){
        terminZeitService.deleteTerminZeits();
        return ResponseEntity.ok().build();
    }


}

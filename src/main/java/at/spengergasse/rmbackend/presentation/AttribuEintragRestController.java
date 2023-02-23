package at.spengergasse.rmbackend.presentation;

import at.spengergasse.rmbackend.Domain.AttributEintrag;
import at.spengergasse.rmbackend.Domain.Reservierung;
import at.spengergasse.rmbackend.persistence.AttributEintragRepository;
import at.spengergasse.rmbackend.service.AttributEintragService;
import at.spengergasse.rmbackend.service.dto.AttributEintragDto;
import at.spengergasse.rmbackend.service.dto.ReserveriungDto;
import at.spengergasse.rmbackend.service.dto.command.MutateAttributEintragCommand;
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
@RequestMapping(AttribuEintragRestController.BASE_URL)
public class AttribuEintragRestController {
    public static final String BASE_URL ="/api/attributEintrag";
    public static final String PATH_INDEX ="/";
    public static final String PATH_VAR_ID ="/{id}";
    public static final String ROUTE_ID = BASE_URL + PATH_VAR_ID;

    private final AttributEintragService attributEintragService;
    private  final AttributEintragRepository attributEintragRepository;

    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<AttributEintragDto>> getAttribuEintrag(){
        List<AttributEintrag> attributEintrag = attributEintragService.getAttributEintrag();

        return(attributEintrag.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(attributEintrag.stream().map(AttributEintragDto::new).toList());
    }

    @GetMapping({PATH_VAR_ID})
    public HttpEntity<AttributEintragDto> getAttById(@PathVariable Long id){
        return attributEintragService.getAttById(id)
                .map(AttributEintragDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping({"",PATH_INDEX})
    public HttpEntity<AttributEintragDto> createAttributEintrag(@RequestBody MutateAttributEintragCommand command){
        AttributEintrag attributEintrag = attributEintragService.createAttributEintrag(command);
        return ResponseEntity.created(createSelfLink(attributEintrag)).body(new AttributEintragDto(attributEintrag));
    }

    private URI createSelfLink(AttributEintrag attributEintrag){
        URI self = UriComponentsBuilder.fromPath(ROUTE_ID)
                .uriVariables(Map.of("id",attributEintrag.getId()))
                .build().toUri();
        return self;
    }

    @PatchMapping({"",PATH_INDEX})
    public HttpEntity<AttributEintragDto> updateAttribuEintrag(@PathVariable Long att_id, @PathVariable Long kr_id, @RequestBody MutateAttributEintragCommand command){
        return ResponseEntity.ok(new AttributEintragDto(attributEintragService.update(att_id,kr_id,command)));
    }

    @DeleteMapping({"",PATH_INDEX})
    public HttpEntity<Void> deleteAttributEintrags(){
        attributEintragService.deleteAttributEintrags();
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping(PATH_VAR_ID)
    public HttpEntity<Void> deleteAttribuEintragById(@PathVariable Long att_id, @PathVariable Long kr_id){
        attributEintragService.deleteAttribuEintrag(att_id,kr_id);
        return ResponseEntity.ok().build();
    }

}

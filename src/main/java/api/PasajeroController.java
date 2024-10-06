package api;

import Servicio.PasajeroService;
import dto.PasajeroDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pasajeros")
public class PasajeroController {
    private final PasajeroService pasajeroService;

    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping()
    public ResponseEntity<List<PasajeroDto>> getAllPasajeros(){
        return ResponseEntity.ok(pasajeroService.buscarPasajeros());
    }

    @GetMapping("/id")
    public ResponseEntity<PasajeroDto> getPasajeroById(@PathVariable Long id){
        return pasajeroService.buscarPasajeroById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PasajeroDto> createPasajero(@RequestBody PasajeroDto pasajeroDto){
        return createNewPasajero(pasajeroDto);
    }

    @PutMapping("/id")
    public ResponseEntity<PasajeroDto> updatePasajero(@PathVariable Long id,@RequestBody PasajeroDto pasajeroDto){
        Optional<PasajeroDto> pasajeroUpdate = pasajeroService.actualizarPasajero(id, pasajeroDto);
        return pasajeroUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewPasajero(pasajeroDto);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<PasajeroDto> deletePasajero(@PathVariable Long id){
        pasajeroService.borrarPasajero(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<PasajeroDto> createNewPasajero(PasajeroDto pasajeroDto) {
        PasajeroDto newPasajero = pasajeroService.guardarPasajero(pasajeroDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPasajero.id())
                .toUri();
        return ResponseEntity.created(location).body(newPasajero);
    }
}

package api;

import Servicio.PasajeroService;
import com.example.taller1.Pasajero;
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
    public ResponseEntity<List<Pasajero>> getAllPasajeros(){
        return ResponseEntity.ok(pasajeroService.buscarPasajeros());
    }

    @GetMapping("/id")
    public ResponseEntity<Pasajero> getPasajeroById(@PathVariable Long id){
        return pasajeroService.buscarPasajeroById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pasajero> createPasajero(@RequestBody Pasajero pasajero){
        return createNewPasajero(pasajero);
    }

    @PutMapping("/id")
    public ResponseEntity<Pasajero> updatePasajero(@PathVariable Long id,@RequestBody Pasajero pasajero){
        Optional<Pasajero> pasajeroUpdate = pasajeroService.actualizarPasajero(id, pasajero);
        return pasajeroUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewPasajero(pasajero);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<Pasajero> deletePasajero(@PathVariable Long id){
        pasajeroService.borrarPasajero(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Pasajero> createNewPasajero(Pasajero pasajero) {
        Pasajero newPasajero = pasajeroService.guardarPasajero(pasajero);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPasajero.getId())
                .toUri();
        return ResponseEntity.created(location).body(newPasajero);
    }
}

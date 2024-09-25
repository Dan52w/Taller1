package api;

import Servicio.AeropuertoService;
import com.example.taller1.Aeropuerto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aeropuestos")
public class AeropuertoController {
    private final AeropuertoService aeropuertoService;

    public AeropuertoController(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    @GetMapping()
    public ResponseEntity<List<Aeropuerto>> getAllAeropuertos() {
        return ResponseEntity.ok(aeropuertoService.buscarAeropuerto());
    }

    @GetMapping("/id")
    public ResponseEntity<Aeropuerto> getAeropuertoById(@PathVariable Long id) {
        return aeropuertoService.buscarAeropuertoById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Aeropuerto> createCliente(@RequestBody Aeropuerto aeropuerto) {
        return createNewCliente(aeropuerto);
    }

    @PutMapping("/id")
    public ResponseEntity<Aeropuerto> updateAeropuerto( @PathVariable Long id, @RequestBody Aeropuerto aeropuerto) {
        Optional<Aeropuerto> aeropuertoUpdate = aeropuertoService.buscarAeropuertoById(id);
        return aeropuertoUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewCliente(aeropuerto);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<Aeropuerto> deleteAeropuerto(@PathVariable Long id) {
        aeropuertoService.borrarAeropuerto(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Aeropuerto> createNewCliente(Aeropuerto aeropuerto) {
        Aeropuerto newAeropueto = aeropuertoService.guardarAeropuerto(aeropuerto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAeropueto.getId())
                .toUri();
        return ResponseEntity.created(location).body(newAeropueto);
    }
}

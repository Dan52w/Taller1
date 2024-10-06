package api;

import Servicio.AeropuertoService;
import com.example.taller1.Aeropuerto;
import dto.AeropuertoDto;
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
    public ResponseEntity<List<AeropuertoDto>> getAllAeropuertos() {
        return ResponseEntity.ok(aeropuertoService.buscarAeropuerto());
    }

    @GetMapping("/id")
    public ResponseEntity<AeropuertoDto> getAeropuertoById(@PathVariable Long id) {
        return aeropuertoService.buscarAeropuertoById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<AeropuertoDto> createCliente(@RequestBody AeropuertoDto aeropuertoDto) {
        return createNewCliente(aeropuertoDto);
    }

    @PutMapping("/id")
    public ResponseEntity<AeropuertoDto> updateAeropuerto( @PathVariable Long id, @RequestBody AeropuertoDto aeropuertoDto) {
        Optional<AeropuertoDto> aeropuertoUpdate = aeropuertoService.buscarAeropuertoById(id);
        return aeropuertoUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewCliente(aeropuertoDto);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<Aeropuerto> deleteAeropuerto(@PathVariable Long id) {
        aeropuertoService.borrarAeropuerto(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<AeropuertoDto> createNewCliente(AeropuertoDto aeropuertoDto) {
        AeropuertoDto newAeropueto = aeropuertoService.guardarAeropuerto(aeropuertoDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAeropueto.id())
                .toUri();
        return ResponseEntity.created(location).body(newAeropueto);
    }
}

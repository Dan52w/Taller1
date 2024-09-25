package api;

import Servicio.AerolineaService;
import com.example.taller1.Aerolinea;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aerolineas")
public class AerolineaController {
    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @GetMapping()
    public ResponseEntity<List<Aerolinea>> getAllAerolineas() {
        return ResponseEntity.ok(aerolineaService.buscarAerolinea());
    }

    @GetMapping("/id")
    public ResponseEntity<Aerolinea> getAerolineaById(@PathVariable Long id) {
        return aerolineaService.buscarAerolineaById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Aerolinea> createAerolinea(@RequestBody Aerolinea aerolinea) {
        return createNewAerolinea(aerolinea);
    }

    @PutMapping("/id")
    public ResponseEntity<Aerolinea> updateAerolinea(@PathVariable Long id,@RequestBody Aerolinea aerolinea) {
        Optional<Aerolinea> aerolineaUpdate = aerolineaService.actualizarAerolinea(id, aerolinea);
        return aerolineaUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewAerolinea(aerolinea);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<Aerolinea> deleteAerolinea(@PathVariable Long id) {
        aerolineaService.borrarAerolinea(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Aerolinea> createNewAerolinea(Aerolinea aerolinea) {
        Aerolinea newAerolinea = aerolineaService.guardarAerolinea(aerolinea);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAerolinea.getId())
                .toUri();
        return ResponseEntity.created(location).body(newAerolinea);
    }
}

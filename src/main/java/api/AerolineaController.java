package api;

import Servicio.AerolineaService;
import dto.AerolineaDto;
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
    public ResponseEntity<List<AerolineaDto>> getAllAerolineas() {
        return ResponseEntity.ok(aerolineaService.buscarAerolinea());
    }

    @GetMapping("/id")
    public ResponseEntity<AerolineaDto> getAerolineaById(@PathVariable Long id) {
        return aerolineaService.buscarAerolineaById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<AerolineaDto> createAerolinea(@RequestBody AerolineaDto aerolineaDto) {
        return createNewAerolinea(aerolineaDto);
    }

    @PutMapping("/id")
    public ResponseEntity<AerolineaDto> updateAerolinea(@PathVariable Long id,@RequestBody AerolineaDto aerolineaDto) {
        Optional<AerolineaDto> aerolineaUpdate = aerolineaService.actualizarAerolinea(id, aerolineaDto);
        return aerolineaUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewAerolinea(aerolineaDto);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<AerolineaDto> deleteAerolinea(@PathVariable Long id) {
        aerolineaService.borrarAerolinea(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<AerolineaDto> createNewAerolinea(AerolineaDto aerolineaDto) {
        AerolineaDto newAerolinea = aerolineaService.guardarAerolinea(aerolineaDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAerolinea.id())
                .toUri();
        return ResponseEntity.created(location).body(newAerolinea);
    }
}

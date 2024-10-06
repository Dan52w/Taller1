package api;


import Servicio.VueloService;
import dto.VueloDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vuelos")
public class VueloController {
    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping()
    public ResponseEntity<List<VueloDto>>  getAllVuelos() {
        return ResponseEntity.ok(vueloService.buscarVuelos());
    }

    @GetMapping("/id")
    public ResponseEntity<VueloDto> getVueloById(@PathVariable Long id) {
        return vueloService.buscarVueloById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VueloDto> createVuelo(@RequestBody VueloDto vueloDto) {
        return createVuelo(vueloDto);
    }

    @PutMapping("/id")
    public ResponseEntity<VueloDto> updateVuelo(@PathVariable Long id, @RequestBody VueloDto vueloDto) {
        Optional<VueloDto> vueloUpdate = vueloService.actualizarVuelo(id, vueloDto);
        return vueloUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createVuelo(vueloDto);
                });
    }

    @DeleteMapping
    public ResponseEntity<VueloDto> deleteVuelo(@PathVariable Long id) {
        vueloService.borrarVuelo(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<VueloDto> createNewVuelo(VueloDto vueloDto) {
        VueloDto newVuelo = vueloService.guardarVuelo(vueloDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVuelo.id())
                .toUri();
        return ResponseEntity.created(location).body(newVuelo);
    }
}

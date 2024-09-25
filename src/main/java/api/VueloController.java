package api;


import Servicio.VueloService;
import com.example.taller1.Vuelo;
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
    public ResponseEntity<List<Vuelo>>  getAllVuelos() {
        return ResponseEntity.ok(vueloService.buscarVuelos());
    }

    @GetMapping("/id")
    public ResponseEntity<Vuelo> getVueloById(@PathVariable Long id) {
        return vueloService.buscarVueloById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vuelo> createVuelo(@RequestBody Vuelo vuelo) {
        return createVuelo(vuelo);
    }

    @PutMapping("/id")
    public ResponseEntity<Vuelo> updateVuelo(@PathVariable Long id,@RequestBody Vuelo vuelo) {
        Optional<Vuelo> vueloUpdate = vueloService.actualizarVuelo(id, vuelo);
        return vueloUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createVuelo(vuelo);
                });
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long id) {
        vueloService.borrarVuelo(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Vuelo> createNewVuelo(Vuelo vuelo) {
        Vuelo newVuelo = vueloService.guardarVuelo(vuelo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVuelo.getId())
                .toUri();
        return ResponseEntity.created(location).body(newVuelo);
    }
}

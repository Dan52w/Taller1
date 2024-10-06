package api;

import Servicio.ReservaService;
import com.example.taller1.Reserva;
import dto.ReservaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping()
    public ResponseEntity<List<ReservaDto>> getAllReservas() {
        return ResponseEntity.ok(reservaService.buscarReserva());
    }

    @GetMapping("/id")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable Long id) {
        return reservaService.buscarReservaById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservaDto> createReserva(@RequestBody ReservaDto reservaDto) {
        return createNewReserva(reservaDto);
    }

    @PutMapping("/id")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable Long id,@RequestBody ReservaDto reservaDto) {
        Optional<ReservaDto> reservaUpdate = reservaService.buscarReservaById(id);
        return reservaUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewReserva(reservaDto);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<Reserva> deleteReserva(@PathVariable Long id) {
        reservaService.borrarReserva(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ReservaDto> createNewReserva(ReservaDto reservaDto) {
        ReservaDto newReserva = reservaService.guardarReserva(reservaDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newReserva.id())
                .toUri();
        return ResponseEntity.created(location).body(newReserva);
    }
}

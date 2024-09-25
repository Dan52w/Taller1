package api;

import Servicio.ReservaService;
import com.example.taller1.Reserva;
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
    public ResponseEntity<List<Reserva>> getAllReservas() {
        return ResponseEntity.ok(reservaService.buscarReserva());
    }

    @GetMapping("/id")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return reservaService.buscarReservaById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        return createNewReserva(reserva);
    }

    @PutMapping("/id")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id,@RequestBody Reserva reserva) {
        Optional<Reserva> reservaUpdate = reservaService.buscarReservaById(id);
        return reservaUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewReserva(reserva);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<Reserva> deleteReserva(@PathVariable Long id) {
        reservaService.borrarReserva(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Reserva> createNewReserva(Reserva reserva) {
        Reserva newReserva = reservaService.guardarReserva(reserva);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newReserva.getId())
                .toUri();
        return ResponseEntity.created(location).body(newReserva);
    }
}

package Servicio;

import com.example.taller1.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    default Reserva guardarReserva(Reserva reserva) {return null;}
    default Optional<Reserva> buscarReservaById(Long id) {return null;}
    default List<Reserva> buscarReservaByNombre(String nombre) {return null;}
    default List<Reserva> buscarReserva() {return null;}
    default List<Reserva> buscarReservaByIds(List<Long> ids) {return null;}
    default Optional<Reserva> actualizarReserva(Long id, Reserva reserva) {return null;}
    void borrarReserva(Long id);
}
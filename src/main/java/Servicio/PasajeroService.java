package Servicio;

import com.example.taller1.Pasajero;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    default Pasajero guardarPasajero(Pasajero pasajero) {return null;}
    default Optional<Pasajero> buscarPasajeroById(Long id) {return null;}
    default List<Pasajero> buscarPasajeros() {return null;}
    default List<Pasajero> buscarPasajerosByNombre(String nombre) {return null;}
    default List<Pasajero> buscarPasajerosByIds(List<Long> ids) {return null;}
    default Optional<Pasajero> actualizarPasajero(Long id,Pasajero pasajero) {return null;}
    void borrarPasajero(Long id);
}

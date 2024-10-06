package Servicio;

import dto.PasajeroDto;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    default PasajeroDto guardarPasajero(PasajeroDto pasajeroDto) {return null;}
    default Optional<PasajeroDto> buscarPasajeroById(Long id) {return null;}
    default List<PasajeroDto> buscarPasajeros() {return null;}
    default List<PasajeroDto> buscarPasajerosByNombre(String nombre) {return null;}
    default List<PasajeroDto> buscarPasajerosByIds(List<Long> ids) {return null;}
    default Optional<PasajeroDto> actualizarPasajero(Long id,PasajeroDto pasajeroDto) {return null;}
    void borrarPasajero(Long id);
}

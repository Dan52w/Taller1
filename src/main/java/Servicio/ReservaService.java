package Servicio;

import dto.ReservaDto;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    default ReservaDto guardarReserva(ReservaDto reservaDto) {return null;}
    default Optional<ReservaDto> buscarReservaById(Long id) {return null;}
    default List<ReservaDto> buscarReservaByNombre(String nombre) {return null;}
    default List<ReservaDto> buscarReserva() {return null;}
    default List<ReservaDto> buscarReservaByIds(List<Long> ids) {return null;}
    default Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto) {return null;}
    void borrarReserva(Long id);
}
package Servicio;

import dto.AeropuertoDto;

import java.util.List;
import java.util.Optional;

public interface AeropuertoService {
    default AeropuertoDto guardarAeropuerto(AeropuertoDto aeropuertoDto) { return null;}
    default Optional<AeropuertoDto> buscarAeropuertoById(Long id) { return null;}
    default List<AeropuertoDto> buscarAeropuertoByNombre(String nombre) { return null;}
    default List<AeropuertoDto> buscarAeropuerto(){ return null;}
    default List<AeropuertoDto> buscarAeropuertoByIds(List<Long> ids) { return null;}
    default Optional<AeropuertoDto> actualizarAeropuerto(Long id,AeropuertoDto aeropuertoDto) { return null;}
    void borrarAeropuerto(Long id);
}

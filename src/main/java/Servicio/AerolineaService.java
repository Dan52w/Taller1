package Servicio;

import dto.AerolineaDto;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {
    default AerolineaDto guardarAerolinea(AerolineaDto aerolineaDto) {return null;}
    default Optional<AerolineaDto> buscarAerolineaById(Long id) {return null;}
    default List<AerolineaDto> buscarAerolineasByNombre(String nombre) {return null;}
    default List<AerolineaDto> buscarAerolinea() {return null;}
    default List<AerolineaDto> buscarAerolineaByIds(List<Long> ids) {return null;}
    default Optional<AerolineaDto> actualizarAerolinea(Long id, AerolineaDto aerolineaDto) {return null;}
    void borrarAerolinea(Long id);
}

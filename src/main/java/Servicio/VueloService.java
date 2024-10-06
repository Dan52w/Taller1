package Servicio;

import dto.VueloDto;

import java.util.List;
import java.util.Optional;

public interface VueloService {
    default VueloDto guardarVuelo(VueloDto vueloDto) {return null;}
    default Optional<VueloDto> buscarVueloById(Long id) {return null;}
    default List<VueloDto> buscarVuelosByNombre(String nombre) {return null;}
    default List<VueloDto> buscarVuelos() {return null;}
    default List<VueloDto> buscarVueloByIds(List<Long> ids) {return null;}
    default Optional<VueloDto> actualizarVuelo(Long id,VueloDto vueloDto) {return null;}
    void borrarVuelo(Long id);
}

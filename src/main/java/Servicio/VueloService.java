package Servicio;

import com.example.taller1.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {
    default Vuelo guardarVuelo(Vuelo vuelo) {return null;}
    default Optional<Vuelo> buscarVueloById(Long id) {return null;}
    default List<Vuelo> buscarVuelosByNombre(String nombre) {return null;}
    default List<Vuelo> buscarVuelos() {return null;}
    default List<Vuelo> buscarVueloByIds(List<Long> ids) {return null;}
    default Optional<Vuelo> actualizarVuelo(Long id,Vuelo vuelo) {return null;}
    void borrarVuelo(Long id);
}

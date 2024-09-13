package Servicio;

import com.example.taller1.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {
    default Aerolinea guardarAerolinea(Aerolinea aerolinea) {return null;}
    default Optional<Aerolinea> buscarAerolineaById(Long id) {return null;}
    default List<Aerolinea> buscarAerolineasByNombre(String nombre) {return null;}
    default List<Aerolinea> buscarAerolinea() {return null;}
    default List<Aerolinea> buscarAerolineaByIds(List<Long> ids) {return null;}
    default Optional<Aerolinea> actualizarAerolinea(Long id, Aerolinea aerolinea) {return null;}
}

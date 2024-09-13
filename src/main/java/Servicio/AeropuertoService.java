package Servicio;

import com.example.taller1.Aeropuerto;

import java.util.List;
import java.util.Optional;

public interface AeropuertoService {
    default Aeropuerto guardarAeropuerto(Aeropuerto aeropuerto) { return null;}
    default Optional<Aeropuerto> buscarAeropuertoById(Long id) { return null;}
    default List<Aeropuerto> buscarAeropuertoByNombre(String nombre) { return null;}
    default List<Aeropuerto> buscarAeropuerto(){ return null;}
    default List<Aeropuerto> buscarAeropuertoByIds(List<Long> ids) { return null;}
    default Optional<Aeropuerto> actualizarAeropuerto(Long id,Aeropuerto aeropuerto) { return null;}
}

package Servicio;

import com.example.taller1.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {
    List<Aeropuerto> findByNombre(String nombre);
    List<Aeropuerto> findByIdIn(Collection<Long> ids);
}

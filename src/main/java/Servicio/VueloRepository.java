package Servicio;

import com.example.taller1.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByNombre(String nombre);
    List<Vuelo> findByIdIn(Collection<Long> ids);
}

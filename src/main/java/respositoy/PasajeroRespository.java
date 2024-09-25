package respositoy;

import com.example.taller1.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PasajeroRespository extends JpaRepository<Pasajero, Long> {
    List<Pasajero> findByNombre(String nombre);
    List<Pasajero> findByIdIn(Collection<Long> ids);
}

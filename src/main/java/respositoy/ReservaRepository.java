package respositoy;

import com.example.taller1.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByNombre(String nombre);
    List<Reserva> findByIdIn(Collection<Long> ids);
}

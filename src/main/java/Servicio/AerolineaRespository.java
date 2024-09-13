package Servicio;

import com.example.taller1.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AerolineaRespository extends JpaRepository<Aerolinea, Long> {
    List<Aerolinea> findByNombre(String aerolinea);
    List<Aerolinea> finByIdIn(List<Long> ids);
}

package Servicio;

import com.example.taller1.Aeropuerto;
import org.springframework.stereotype.Service;
import respositoy.AeropuertoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AeropuertoServiceImpl implements AeropuertoService{
    private AeropuertoRepository aeropuertoRepository;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public Aeropuerto guardarAeropuerto(Aeropuerto aeropuerto) {
        return aeropuertoRepository.save(aeropuerto);
    }

    @Override
    public Optional<Aeropuerto> buscarAeropuertoById(Long id) {
        return aeropuertoRepository.findById(id);
    }

    @Override
    public List<Aeropuerto> buscarAeropuertoByNombre(String nombre) {
        return aeropuertoRepository.findByNombre(nombre);
    }

    @Override
    public List<Aeropuerto> buscarAeropuerto() {
        return aeropuertoRepository.findAll();
    }

    @Override
    public List<Aeropuerto> buscarAeropuertoByIds(List<Long> ids) {
        return aeropuertoRepository.findByIdIn(ids);
    }

    @Override
    public Optional<Aeropuerto> actualizarAeropuerto(Long id, Aeropuerto aeropuerto) {
        return aeropuertoRepository.findById(id).map(oldAeropuerto ->{
            oldAeropuerto.setNombre(aeropuerto.getNombre());
            oldAeropuerto.setCiudad(aeropuerto.getCiudad());
            oldAeropuerto.setPais(aeropuerto.getPais());
            return aeropuertoRepository.save(oldAeropuerto);
        });
    }
}

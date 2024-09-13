package Servicio;

import com.example.taller1.Aerolinea;

import java.util.List;
import java.util.Optional;

public class AerolineaServiceImpl implements AerolineaService {
    private AerolineaRespository aerolineaRepository;

    public AerolineaServiceImpl(AerolineaRespository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    @Override
    public Aerolinea guardarAerolinea(Aerolinea aerolinea) {
        return aerolineaRepository.save(aerolinea);
    }

    @Override
    public Optional<Aerolinea> buscarAerolineaById(Long id) {
        return aerolineaRepository.findById(id);
    }

    @Override
    public List<Aerolinea> buscarAerolineasByNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre);
    }

    @Override
    public List<Aerolinea> buscarAerolinea() {
        return aerolineaRepository.findAll();
    }

    @Override
    public List<Aerolinea> buscarAerolineaByIds(List<Long> ids) {
        return aerolineaRepository.finByIdIn(ids);
    }

    @Override
    public Optional<Aerolinea> actualizarAerolinea(Long id, Aerolinea aerolinea) {
        return aerolineaRepository.findById(id).map(oldAerolinea -> {
            oldAerolinea.setNombre(aerolinea.getNombre());
            oldAerolinea.setCodigoAerolinea(aerolinea.getCodigoAerolinea());
            oldAerolinea.setPaisOrigen(aerolinea.getPaisOrigen());
            return aerolineaRepository.save(oldAerolinea);
        });
    }
}

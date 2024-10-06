package Servicio;

import com.example.taller1.Aerolinea;
import dto.AerolineaDto;
import dto.AerolineaMapper;
import org.springframework.stereotype.Service;
import respositoy.AerolineaRespository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {
    private AerolineaRespository aerolineaRepository;
    private AerolineaMapper aerolineaMapper;

    public AerolineaServiceImpl(AerolineaRespository aerolineaRepository, AerolineaMapper aerolineaMapper) {
        this.aerolineaRepository = aerolineaRepository;
        this.aerolineaMapper = aerolineaMapper;
    }

    @Override
    public AerolineaDto guardarAerolinea(AerolineaDto aerolineaDto) {
        Aerolinea aerolinea = aerolineaMapper.INSTANCE.toAerolineaWithId(aerolineaDto);
        return aerolineaMapper.toAerolineaDto(aerolineaRepository.save(aerolinea));
    }

    @Override
    public Optional<AerolineaDto> buscarAerolineaById(Long id) {
        return Optional.ofNullable(aerolineaMapper.INSTANCE.toAerolineaDtoWithId(aerolineaRepository.findById(id).get()));
    }

    @Override
    public List<AerolineaDto> buscarAerolineasByNombre(String nombre) {
        List<Aerolinea> aerolineas = aerolineaRepository.findByNombre(nombre);
        return ListAerolineasToListAerolineasDto(aerolineas);
    }

    @Override
    public List<AerolineaDto> buscarAerolinea() {
        List<Aerolinea> aerolineas = aerolineaRepository.findAll();
        return ListAerolineasToListAerolineasDto(aerolineas);
    }

    @Override
    public List<AerolineaDto> buscarAerolineaByIds(List<Long> ids) {
        List<Aerolinea> aerolineas = aerolineaRepository.finByIdIn(ids);
        return ListAerolineasToListAerolineasDto(aerolineas);
    }

    @Override
    public Optional<AerolineaDto> actualizarAerolinea(Long id, AerolineaDto aerolineaDto) {
        return aerolineaRepository.findById(id).map(oldAerolinea -> {
            oldAerolinea.setNombre(aerolineaDto.nombre());
            oldAerolinea.setCodigoAerolinea(aerolineaDto.codigoAerolinea());
            oldAerolinea.setPaisOrigen(aerolineaDto.paisOrigen());
            return aerolineaMapper.INSTANCE.toAerolineaDtoWithId(aerolineaRepository.save(oldAerolinea));
        });
    }

    @Override
    public void borrarAerolinea(Long id) {
        aerolineaRepository.deleteById(id);
    }

    private List<AerolineaDto> ListAerolineasToListAerolineasDto(List<Aerolinea> aerolineas) {
        List<AerolineaDto> aerolineasDto = new ArrayList<>();
        for (Aerolinea aerolinea : aerolineas) {
            aerolineasDto.add(aerolineaMapper.INSTANCE.toAerolineaDtoWithId(aerolinea));
        }
        return aerolineasDto;
    }
}

package Servicio;

import com.example.taller1.Aeropuerto;
import dto.AeropuertoDto;
import dto.AeropuertoMapper;
import org.springframework.stereotype.Service;
import respositoy.AeropuertoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AeropuertoServiceImpl implements AeropuertoService{
    private AeropuertoRepository aeropuertoRepository;
    private AeropuertoMapper aeropuertoMapper;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository, AeropuertoMapper aeropuertoMapper) {
        this.aeropuertoRepository = aeropuertoRepository;
        this.aeropuertoMapper = aeropuertoMapper;
    }

    @Override
    public AeropuertoDto guardarAeropuerto(AeropuertoDto aeropuertoDto) {
        Aeropuerto aeropuerto = aeropuertoMapper.INSTANCE.toAeropuerto(aeropuertoDto);
        return aeropuertoMapper.INSTANCE.toAeropuertoDto(aeropuertoRepository.save(aeropuerto));
    }

    @Override
    public Optional<AeropuertoDto> buscarAeropuertoById(Long id) {
        return Optional.ofNullable(aeropuertoMapper.INSTANCE.toAeropuertoDto(aeropuertoRepository.findById(id).get()));
    }

    @Override
    public List<AeropuertoDto> buscarAeropuertoByNombre(String nombre) {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findByNombre(nombre);
        return ToListAeropuertosDto(aeropuertos);
    }

    @Override
    public List<AeropuertoDto> buscarAeropuerto() {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findAll();
        return ToListAeropuertosDto(aeropuertos);
    }

    @Override
    public List<AeropuertoDto> buscarAeropuertoByIds(List<Long> ids) {
        List<Aeropuerto> aeropuertos = aeropuertoRepository.findByIdIn(ids);
        return ToListAeropuertosDto(aeropuertos);
    }

    @Override
    public Optional<AeropuertoDto> actualizarAeropuerto(Long id, AeropuertoDto aeropuertoDto) {
        return aeropuertoRepository.findById(id).map(oldAeropuerto ->{
            oldAeropuerto.setNombre(aeropuertoDto.nombre());
            oldAeropuerto.setCiudad(aeropuertoDto.ciudad());
            oldAeropuerto.setPais(aeropuertoDto.pais());
            return aeropuertoMapper.INSTANCE.toAeropuertoDto(aeropuertoRepository.save(oldAeropuerto));
        });
    }

    @Override
    public void borrarAeropuerto(Long id) {
        aeropuertoRepository.deleteById(id);
    }

    private List<AeropuertoDto> ToListAeropuertosDto(List<Aeropuerto> aeropuertos) {
        List<AeropuertoDto> aeropuertosDto = new ArrayList<>();
        for (Aeropuerto aeropuerto : aeropuertos) {
            aeropuertosDto.add(aeropuertoMapper.toAeropuertoDto(aeropuerto));
        }
        return aeropuertosDto;
    }
}

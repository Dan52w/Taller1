package Servicio;

import com.example.taller1.Pasajero;
import dto.PasajeroDto;
import dto.PasajeroMapper;
import org.springframework.stereotype.Service;
import respositoy.PasajeroRespository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PasajeroServiceImpl implements PasajeroService{
    private PasajeroRespository pasajeroRepository;
    private PasajeroMapper pasajeroMapper;

    public PasajeroServiceImpl(PasajeroRespository pasajeroRepository, PasajeroMapper pasajeroMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
    }

    @Override
    public PasajeroDto guardarPasajero(PasajeroDto pasajeroDto) {
        Pasajero pasajero = pasajeroMapper.toPasajero(pasajeroDto);
        return pasajeroMapper.INSTANCE.toPasajeroDto(pasajeroRepository.save(pasajero));
    }

    @Override
    public Optional<PasajeroDto> buscarPasajeroById(Long id) {
        return pasajeroRepository.findById(id).map(pasajeroMapper::toPasajeroDto);
    }

    @Override
    public List<PasajeroDto> buscarPasajerosByNombre(String nombre) {
        List<Pasajero> pasajeros = pasajeroRepository.findByNombre(nombre);
        return ToListPasajerosDto(pasajeros);
    }

    @Override
    public List<PasajeroDto> buscarPasajeros() {
        List<Pasajero> pasajeros = pasajeroRepository.findAll();
        return ToListPasajerosDto(pasajeros);
    }

    @Override
    public List<PasajeroDto> buscarPasajerosByIds(List<Long> ids) {
        List<Pasajero> pasajeros = pasajeroRepository.findByIdIn(ids);
        return ToListPasajerosDto(pasajeros);
    }

    @Override
    public Optional<PasajeroDto> actualizarPasajero(Long id, PasajeroDto pasajeroDto) {
        return pasajeroRepository.findById(id).map(oldPasajero ->{
            oldPasajero.setNombre(pasajeroDto.nombre());
            oldPasajero.setApellido(pasajeroDto.apellido());
            oldPasajero.setEdad(pasajeroDto.edad());
            oldPasajero.setCedula(pasajeroDto.cedula());
            oldPasajero.setSexo(pasajeroDto.sexo());
            oldPasajero.setFechaVuelo(pasajeroDto.fechaVuelo());
            return pasajeroMapper.INSTANCE.toPasajeroDto(pasajeroRepository.save(oldPasajero));
        });
    }

    @Override
    public void borrarPasajero(Long id) {
        pasajeroRepository.deleteById(id);
    }

    private List<PasajeroDto> ToListPasajerosDto(List<Pasajero> pasajeros) {
        List<PasajeroDto> pasajeroDtos = new ArrayList<>();
        for (Pasajero pasajero : pasajeros) {
            pasajeroDtos.add(pasajeroMapper.INSTANCE.toPasajeroDto(pasajero));
        }
        return pasajeroDtos;
    }
}

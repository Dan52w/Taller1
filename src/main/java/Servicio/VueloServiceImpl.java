package Servicio;

import com.example.taller1.Vuelo;
import dto.VueloDto;
import dto.VueloMapper;
import org.springframework.stereotype.Service;
import respositoy.VueloRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService{
    private VueloRepository vueloRepository;
    private VueloMapper vueloMapper;

    public VueloServiceImpl(VueloRepository vueloRepository, VueloMapper vueloMapper) {
        this.vueloRepository = vueloRepository;
        this.vueloMapper = vueloMapper;
    }

    @Override
    public VueloDto guardarVuelo(VueloDto vueloDto) {
        Vuelo vuelo = vueloMapper.INSTANCE.toVuelo(vueloDto);;
        return vueloMapper.INSTANCE.toVueloDto(vueloRepository.save(vuelo));
    }

    @Override
    public Optional<VueloDto> buscarVueloById(Long id) {
        return vueloRepository.findById(id).map(vueloMapper::toVueloDto);
    }

    @Override
    public List<VueloDto> buscarVuelosByNombre(String nombre) {
        List<Vuelo> vuelos = vueloRepository.findByNombre(nombre);
        return ToListVuelosDto(vuelos);
    }

    @Override
    public List<VueloDto> buscarVuelos() {
        List<Vuelo> vuelos = vueloRepository.findAll();
        return ToListVuelosDto(vuelos);
    }

    @Override
    public List<VueloDto> buscarVueloByIds(List<Long> ids) {
        List<Vuelo> vuelos = vueloRepository.findByIdIn(ids);
        return ToListVuelosDto(vuelos);
    }

    @Override
    public Optional<VueloDto> actualizarVuelo(Long id, VueloDto vueloDto) {
        return vueloRepository.findById(id).map(oldVuelo ->{
            oldVuelo.setCapacidad(vueloDto.capacidad());
            oldVuelo.setDestino(vueloDto.destino());
            oldVuelo.setOrigen(vueloDto.origen());
            oldVuelo.setHoraLlegada(vueloDto.horaLlegada());
            oldVuelo.setDuracion(vueloDto.duracion());
            oldVuelo.setFechaSalida(vueloDto.fechaSalida());
            return vueloMapper.INSTANCE.toVueloDto(vueloRepository.save(oldVuelo));
        });
    }

    @Override
    public void borrarVuelo(Long id) {
        vueloRepository.deleteById(id);
    }

    private List<VueloDto> ToListVuelosDto(List<Vuelo> vuelos) {
        List<VueloDto> vueloDtos = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            vueloDtos.add(vueloMapper.toVueloDto(vuelo));
        }
        return vueloDtos;
    }
}

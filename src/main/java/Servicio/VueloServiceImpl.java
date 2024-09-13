package Servicio;

import com.example.taller1.Vuelo;

import java.util.List;
import java.util.Optional;

public class VueloServiceImpl implements VueloService{
    private VueloRepository vueloRepository;

    public VueloServiceImpl(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @Override
    public Vuelo guardarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Optional<Vuelo> buscarVueloById(Long id) {
        return vueloRepository.findById(id);
    }

    @Override
    public List<Vuelo> buscarVuelosByNombre(String nombre) {
        return vueloRepository.findByNombre(nombre);
    }

    @Override
    public List<Vuelo> buscarVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    public List<Vuelo> buscarVueloByIds(List<Long> ids) {
        return vueloRepository.findByIdIn(ids);
    }

    @Override
    public Optional<Vuelo> actualizarVuelo(Long id, Vuelo vuelo) {
        return vueloRepository.findById(id).map(oldVuelo ->{
            oldVuelo.setCapacidad(vuelo.getCapacidad());
            oldVuelo.setDestino(vuelo.getDestino());
            oldVuelo.setOrigen(vuelo.getOrigen());
            oldVuelo.setHoraLlegada(vuelo.getHoraLlegada());
            oldVuelo.setDuracion(vuelo.getDuracion());
            oldVuelo.setFechaSalida(vuelo.getFechaSalida());
            return vueloRepository.save(oldVuelo);
        });
    }
}

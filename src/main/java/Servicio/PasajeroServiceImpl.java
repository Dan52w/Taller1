package Servicio;

import com.example.taller1.Pasajero;

import java.util.List;
import java.util.Optional;

public class PasajeroServiceImpl implements PasajeroService{
    private PasajeroRespository pasajeroRepository;

    public PasajeroServiceImpl(PasajeroRespository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    @Override
    public Pasajero guardarPasajero(Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    @Override
    public Optional<Pasajero> buscarPasajeroById(Long id) {
        return pasajeroRepository.findById(id);
    }

    @Override
    public List<Pasajero> buscarPasajerosByNombre(String nombre) {
        return pasajeroRepository.findByNombre(nombre);
    }

    @Override
    public List<Pasajero> buscarPasajeros() {
        return pasajeroRepository.findAll();
    }

    @Override
    public List<Pasajero> buscarPasajerosByIds(List<Long> ids) {
        return pasajeroRepository.findByIdIn(ids);
    }

    @Override
    public Optional<Pasajero> actualizarPasajero(Long id, Pasajero pasajero) {
        return pasajeroRepository.findById(id).map(oldPasajero ->{
            oldPasajero.setNombre(pasajero.getNombre());
            oldPasajero.setApellido(pasajero.getApellido());
            oldPasajero.setEdad(pasajero.getEdad());
            oldPasajero.setCedula(pasajero.getCedula());
            oldPasajero.setSexo(pasajero.getSexo());
            oldPasajero.setFechaVuelo(pasajero.getFechaVuelo());
            return pasajeroRepository.save(oldPasajero);
        });
    }
}

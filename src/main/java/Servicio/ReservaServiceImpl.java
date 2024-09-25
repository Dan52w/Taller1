package Servicio;

import com.example.taller1.Reserva;
import respositoy.ReservaRepository;

import java.util.List;
import java.util.Optional;

public class ReservaServiceImpl implements ReservaService {
    private ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> buscarReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public List<Reserva> buscarReservaByNombre(String nombre) {
        return reservaRepository.findByNombre(nombre);
    }

    @Override
    public List<Reserva> buscarReserva() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> buscarReservaByIds(List<Long> ids) {
        return reservaRepository.findAllById(ids);
    }

    @Override
    public Optional<Reserva> actualizarReserva(Long id, Reserva reserva) {
        return reservaRepository.findById(id).map(oldReserva -> {
            oldReserva.setId(reserva.getId());
            oldReserva.setFechaReserva(reserva.getFechaReserva());
            return reservaRepository.save(oldReserva);
        });
    }

    @Override
    public void borrarReserva(Long id) {

    }
}

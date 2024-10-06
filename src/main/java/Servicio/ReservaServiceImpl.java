package Servicio;

import com.example.taller1.Reserva;
import dto.ReservaDto;
import dto.ReservaMapper;
import org.springframework.stereotype.Service;
import respositoy.ReservaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {
    private ReservaRepository reservaRepository;
    private ReservaMapper reservaMapper;

    public ReservaServiceImpl(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    @Override
    public ReservaDto guardarReserva(ReservaDto reservaDto) {
        Reserva reserva = reservaMapper.toReserva(reservaDto);
        return reservaMapper.INSTANCE.toReservaDto(reservaRepository.save(reserva));
    }

    @Override
    public Optional<ReservaDto> buscarReservaById(Long id) {
        return reservaRepository.findById(id).map(reservaMapper::toReservaDto);
    }

    @Override
    public List<ReservaDto> buscarReservaByNombre(String nombre) {
        List<Reserva> reservas = reservaRepository.findByNombre(nombre);
        return ToListReservasDto(reservas);
    }

    @Override
    public List<ReservaDto> buscarReserva() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ToListReservasDto(reservas);
    }

    @Override
    public List<ReservaDto> buscarReservaByIds(List<Long> ids) {
        List<Reserva> reservas = reservaRepository.findAllById(ids);
        return ToListReservasDto(reservas);
    }

    @Override
    public Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto) {
        return reservaRepository.findById(id).map(oldReserva -> {
            oldReserva.setId(reservaDto.id());
            oldReserva.setFechaReserva(reservaDto.fechaReserva());
            oldReserva.setNumeroPasajeros(reservaDto.numeroPasajeros());
            return reservaMapper.INSTANCE.toReservaDto(reservaRepository.save(oldReserva));
        });
    }

    @Override
    public void borrarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    private List<ReservaDto> ToListReservasDto(List<Reserva> reservas) {
        List<ReservaDto> reservasDto = new ArrayList<>();
        for (Reserva reserva : reservas) {
            reservasDto.add(reservaMapper.INSTANCE.toReservaDto(reserva));
        }
        return reservasDto;
    }
}

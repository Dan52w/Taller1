package dto;

import com.example.taller1.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservaMapper {

    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Reserva toReservaSinID(ReservaDto reservaDto);

    Reserva toReserva(ReservaDto reservaDto);

    ReservaDto toReservaDto(Reserva reserva);

    @Mapping(source = "id", target = "id", ignore = true)
    ReservaDto toReservaDtoSinID(Reserva reserva);
}

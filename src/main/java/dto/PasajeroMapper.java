package dto;

import com.example.taller1.Pasajero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PasajeroMapper {

    PasajeroMapper INSTANCE = Mappers.getMapper(PasajeroMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Pasajero toPasajeroSinID(PasajeroDto pasajeroDto);

    Pasajero toPasajero(PasajeroDto pasajeroDto);

    PasajeroDto toPasajeroDto(Pasajero pasajero);

    @Mapping(source = "id", target = "id", ignore = true)
    PasajeroDto toPasajeroDtoSinID(Pasajero pasajero);
}

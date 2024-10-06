package dto;

import com.example.taller1.Aeropuerto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AeropuertoMapper {
    AeropuertoMapper INSTANCE = Mappers.getMapper(AeropuertoMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Aeropuerto toAerolineaSinID(AeropuertoDto aeropuertoDto);

    Aeropuerto toAeropuerto(AeropuertoDto aeropuertoDto);

    AeropuertoDto toAeropuertoDto(Aeropuerto aeropuerto);

    @Mapping(source = "id", target = "id", ignore = true)
    AeropuertoDto toAeropuertoDtoSinID(Aeropuerto aeropuerto);
}

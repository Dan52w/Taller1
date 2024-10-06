package dto;

import com.example.taller1.Aerolinea;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AerolineaMapper {
    AerolineaMapper INSTANCE = Mappers.getMapper(AerolineaMapper.class);

    @Name("SinID")
    @Mapping(source = "id", target = "id", ignore = true)
    Aerolinea toAerolinea(AerolineaDto aerolineaDto);

    @Name("ConID")
    Aerolinea toAerolineaWithId(AerolineaDto aerolineaDto);

    @Name("SinID")
    @Mapping(source = "id", target = "id", ignore = true)
    AerolineaDto toAerolineaDto(Aerolinea aerolinea);

    @Name("ConID")
    AerolineaDto toAerolineaDtoWithId(Aerolinea aerolinea);
}

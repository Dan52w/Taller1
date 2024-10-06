package dto;

import com.example.taller1.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VueloMapper {
    VueloMapper INSTANCE = Mappers.getMapper(VueloMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Vuelo toVueloSinID(VueloDto vueloDto);

    Vuelo toVuelo(VueloDto vueloDto);

    VueloDto toVueloDto(Vuelo vuelo);

    @Mapping(source = "id", target = "id", ignore = true)
    VueloDto toVueloDtoSinID(VueloDto vueloDto);
}

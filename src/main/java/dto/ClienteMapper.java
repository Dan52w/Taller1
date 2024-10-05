package dto;

import com.example.taller1.Cliente;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    //Target es el del ClienteDto y Source es el del cliente
    @Name("SinID")
    @Mapping(target = "id", source = "id", ignore = true)
    Cliente ToCliente(ClienteDto clienteDto);

    @Name("ConID")
    Cliente ToClienteWithId(ClienteDto clienteDto);

    @Name("ConID")
    @Mapping(target = "nombre", source = "nombre")
    ClienteDto ToClienteDto(Cliente cliente);

    @Name("SinID")
    @Mapping(target = "id", source = "id", ignore = true)
    ClienteDto ToClienteDtoWithId(Cliente cliente);
}

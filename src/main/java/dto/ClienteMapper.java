package dto;

import com.example.taller1.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    //Target es el del ClienteDto y Source es el del cliente
    @Mapping(target = "nombre", source = "nombre")
    Cliente ClienteDtoToCliente(ClienteDto clienteDto);

    @Mapping(target = "nombre", source = "nombre")
    ClienteDto ClienteToClienteDto(Cliente cliente);

    ClienteWithIDDto ClienteToClienteWithIDDto(Cliente cliente);
    Cliente clienteWithIDDtoToCliente(ClienteWithIDDto clienteWithIDDto);
}

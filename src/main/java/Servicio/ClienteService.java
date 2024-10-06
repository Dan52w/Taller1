package Servicio;

import dto.ClienteDto;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    default ClienteDto guardarCliente(ClienteDto clienteDto) { return null;}
    default Optional<ClienteDto> buscarClienteById(Long id) { return null;}
    default List<ClienteDto> buscarClienteByNombre(String nombre) { return null;}
    default List<ClienteDto> buscarCliente() {return null;}
    default List<ClienteDto> buscarClientebyIds(List<Long> ids) {return null;}
    default Optional<ClienteDto> actualizarCliente(Long id, ClienteDto cliente) { return null;}
    void borrarCliente(Long id);
}

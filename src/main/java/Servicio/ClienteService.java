package Servicio;

import com.example.taller1.Cliente;
import dto.ClienteDto;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    default Cliente guardarCliente(ClienteDto cliente) { return null;}
    default Optional<Cliente> buscarClienteById(Long id) { return null;}
    default List<Cliente> buscarClienteByNombre(String nombre) { return null;}
    default List<Cliente> buscarCliente() {return null;}
    default List<Cliente> buscarClientebyIds(List<Long> ids) {return null;}
    default Optional<Cliente> actualizarCliente(Long id,Cliente cliente) { return null;}
    void borrarCliente(Long id);
}

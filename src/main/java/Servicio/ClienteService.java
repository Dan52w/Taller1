package Servicio;

import com.example.taller1.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    default Cliente guardarCliente(Cliente cliente) { return null;}
    default Optional<Cliente> buscarClienteById(Long id) { return null;}
    default List<Cliente> buscarClienteByNombre(String nombre) { return null;}
    default List<Cliente> buscarCliente() {return null;}
    default List<Cliente> buscarClientebyIds(List<Long> ids) {return null;}
    default Optional<Cliente> actualizarCliente(Long id,Cliente cliente) { return null;}
}

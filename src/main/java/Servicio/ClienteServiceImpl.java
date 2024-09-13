package Servicio;

import com.example.taller1.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService{
    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> buscarClienteByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public List<Cliente> buscarClientebyIds(List<Long> ids) {
        return clienteRepository.findByIdIn(ids);
    }

    @Override
    public List<Cliente> buscarCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> actualizarCliente(Long id, Cliente cliente) {
        return clienteRepository.findById(id).map(oldClient ->{
            oldClient.setNombre(cliente.getNombre());
            oldClient.setApellido(cliente.getApellido());
            oldClient.setEmail(cliente.getEmail());
            oldClient.setTelefono(cliente.getTelefono());
            oldClient.setFechaNacimiento(cliente.getFechaNacimiento());
            oldClient.setDirreccion(cliente.getDirreccion());
            return clienteRepository.save(oldClient);
        });
    }
}

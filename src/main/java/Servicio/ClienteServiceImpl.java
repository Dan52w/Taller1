package Servicio;

import com.example.taller1.Cliente;
import dto.ClienteDto;
import dto.ClienteMapper;
import dto.ClienteWithIDDto;
import org.springframework.stereotype.Service;
import respositoy.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{
    private ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteWithIDDto guardarCliente(ClienteDto cliente) {
        Cliente clienteEntity = clienteMapper.INSTANCE.ClienteDtoToCliente(cliente);
        ClienteWithIDDto clienteWithIDDto = clienteMapper.ClienteToClienteWithIDDto(clienteEntity);
        clienteRepository.save(clienteEntity);
        return clienteWithIDDto;
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

    @Override
    public void borrarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}

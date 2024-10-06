package Servicio;

import com.example.taller1.Cliente;
import dto.ClienteDto;
import dto.ClienteMapper;
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
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        Cliente clienteEntity = clienteMapper.INSTANCE.ToCliente(clienteDto);
        clienteRepository.save(clienteEntity);
        return clienteMapper.INSTANCE.ToClienteDtoWithId(clienteEntity);
    }

    @Override
    public Optional<ClienteDto> buscarClienteById(Long id) {
        return Optional.ofNullable(clienteMapper.INSTANCE.ToClienteDtoWithId(clienteRepository.findById(id).get()));
    }

    @Override
    public List<ClienteDto> buscarClienteByNombre(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombre(nombre);
        return ListClientesToListClientesDto(clientes);
    }

    @Override
    public List<ClienteDto> buscarClientebyIds(List<Long> ids) {
        List<Cliente> clientes = clienteRepository.findByIdIn(ids);
        return ListClientesToListClientesDto(clientes);
    }

    @Override
    public List<ClienteDto> buscarCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ListClientesToListClientesDto(clientes);
    }

    @Override
    public Optional<ClienteDto> actualizarCliente(Long id, ClienteDto clienteDto) {
        return clienteRepository.findById(id).map(oldClient ->{
            oldClient.setNombre(clienteDto.nombre());
            oldClient.setApellido(clienteDto.apellido());
            oldClient.setEmail(clienteDto.email());
            oldClient.setTelefono(clienteDto.telefono());
            oldClient.setFechaNacimiento(clienteDto.fechaNacimiento());
            oldClient.setDirreccion(clienteDto.dirreccion());
            clienteRepository.save(oldClient);
            return clienteMapper.INSTANCE.ToClienteDtoWithId(oldClient);
        });
    }

    @Override
    public void borrarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    private List<ClienteDto> ListClientesToListClientesDto(List<Cliente> clientes) {
        List<ClienteDto> clienteDtos = null;
        for (Cliente cliente : clientes) {
            clienteDtos.add(clienteMapper.INSTANCE.ToClienteDto(cliente));
        }
        return clienteDtos;
    }
}

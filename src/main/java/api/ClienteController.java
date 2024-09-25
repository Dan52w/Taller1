package api;

import Servicio.ClienteService;
import com.example.taller1.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>>  getAllClientes() {
        return ResponseEntity.ok(clienteService.buscarCliente());
    }

    //Si el nombre del Param es igual al de abajo se asume, sino se especifica con () despues del path
    @GetMapping("/id")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        //Opcion fea de hacerlo
        /*
        Optional<Cliente> cliente = clienteService.buscarClienteById(id);
        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        */
        return clienteService.buscarClienteById(id)
                .map(c -> ResponseEntity.ok().body(c)) //Si todo va bien convierte el objeto y lo retorna
                .orElse(ResponseEntity.notFound().build()); //Si todo va mal retorna una Excepcion en tiempo de Ejecucion
    }

    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return createNewCliente(cliente); //La buena practica
        /* Mala practica
        if(Objects.nonNull(newCliente)){
            return ResponseEntity.created(new URI("/api/v1/clientes/" + newCliente.getId()).body(newCliente));
        }
        */
    }

    @PutMapping("/id")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id,@RequestBody Cliente cliente) {
        Optional<Cliente> clienteUpdate = clienteService.actualizarCliente(id, cliente);
        return clienteUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() ->{
                    return createNewCliente(cliente);
                });
    }

    private ResponseEntity<Cliente> createNewCliente(Cliente cliente) {
        Cliente newCliente = clienteService.guardarCliente(cliente); //Esto me va a retornar un cliente
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCliente.getId())
                .toUri(); //Construye la Url que luego se usa en location abajo, en el 201 que es el Created
        return ResponseEntity.created(location).body(newCliente);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id) {
        clienteService.borrarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
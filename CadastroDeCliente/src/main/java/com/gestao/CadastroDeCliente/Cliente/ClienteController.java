package com.gestao.CadastroDeCliente.Cliente;


import com.gestao.CadastroDeCliente.Categoria.CategoriaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        List<ClienteDTO> listaClientes = clienteService.listarCliente();
        if (listaClientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return  ResponseEntity.ok(listaClientes);
        }

    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<ClienteDTO> listarClienteID(@PathVariable Long id){
        ClienteDTO clienteID = clienteService.listarClienteID(id);
        return ResponseEntity.ok(clienteID);
    }

    @PostMapping("/add")
    public ResponseEntity<ClienteDTO> addCliente(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO NovoCliente = clienteService.addCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(NovoCliente);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        if (clienteService.listarClienteID(id) != null){
            clienteService.atualizarCliente(id, clienteDTO);
            return ResponseEntity.ok("Cliente atualizado com sucesso!");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não encontrado.");
        }
    }

}

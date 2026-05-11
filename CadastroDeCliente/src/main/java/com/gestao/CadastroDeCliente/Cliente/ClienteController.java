package com.gestao.CadastroDeCliente.Cliente;


import com.gestao.CadastroDeCliente.Categoria.CategoriaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (listaClientes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return  ResponseEntity.ok(listaClientes);
        }

    }

}

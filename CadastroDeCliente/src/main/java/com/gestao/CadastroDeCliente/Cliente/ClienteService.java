package com.gestao.CadastroDeCliente.Cliente;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {


    private ClienteRepository clienteRepository;
    private ClienteMAPPER clienteMAPPER;

    public ClienteService(ClienteRepository clienteRepository, ClienteMAPPER clienteMAPPER) {
        this.clienteRepository = clienteRepository;
        this.clienteMAPPER = clienteMAPPER;
    }


    //listar todos os clientes

    public List<ClienteDTO> listarCliente(){
        List<ClienteModel> clientesModels = clienteRepository.findAll();
        return clientesModels.stream()
                .map(clienteMAPPER::map)
                .collect(Collectors.toList());

    }
}

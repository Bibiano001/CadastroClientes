package com.gestao.CadastroDeCliente.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteMAPPER {

    public ClienteModel map(ClienteDTO clienteDTO){


        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteDTO.getId());
        clienteModel.setNome(clienteDTO.getNome());
        clienteModel.setIdade(clienteDTO.getIdade());
        clienteModel.setEmail(clienteDTO.getEmail());
        clienteModel.setCategoria(clienteDTO.getCategoria());

        return clienteModel;

    }

    public ClienteDTO map(ClienteModel clienteModel){

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteModel.getId());
        clienteDTO.setNome(clienteModel.getNome());
        clienteDTO.setIdade(clienteModel.getIdade());
        clienteDTO.setEmail(clienteModel.getEmail());
        clienteDTO.setCategoria(clienteModel.getCategoria());

        return clienteDTO;
    }
}

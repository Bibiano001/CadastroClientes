package com.gestao.CadastroDeCliente.cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    // A categoria é vinculada no ClienteService, porque precisa ser buscada no banco
    public ClienteModel map(ClienteDTO clienteDTO) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteDTO.getId());
        clienteModel.setNome(clienteDTO.getNome());
        clienteModel.setEmail(clienteDTO.getEmail());
        clienteModel.setIdade(clienteDTO.getIdade());
        return clienteModel;
    }

    public ClienteDTO map(ClienteModel clienteModel) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteModel.getId());
        clienteDTO.setNome(clienteModel.getNome());
        clienteDTO.setEmail(clienteModel.getEmail());
        clienteDTO.setIdade(clienteModel.getIdade());
        if (clienteModel.getCategoria() != null) {
            clienteDTO.setCategoriaId(clienteModel.getCategoria().getId());
        }
        return clienteDTO;
    }
}

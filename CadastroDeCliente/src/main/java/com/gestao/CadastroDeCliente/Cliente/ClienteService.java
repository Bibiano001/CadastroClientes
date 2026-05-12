package com.gestao.CadastroDeCliente.Cliente;

import org.apache.el.lang.ELArithmetic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    //listar cliente por ID
    public ClienteDTO listarClienteID(Long id){
        Optional<ClienteModel> clienteID = clienteRepository.findById(id);
        return clienteID.map(clienteMAPPER::map).orElse(null);
    }

    //Adicionar Cliente

    public ClienteDTO addCliente(ClienteDTO clienteDTO){
        ClienteModel clienteModel = clienteMAPPER.map(clienteDTO);
        clienteRepository.save(clienteModel);
        return clienteMAPPER.map(clienteModel);
    }

    //Atualizar Cliente

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO){
        Optional<ClienteModel> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()){
            ClienteModel clienteAtualizado = clienteMAPPER.map(clienteDTO);
            clienteAtualizado.setId(id);
            ClienteModel clienteSalvo = clienteRepository.save(clienteAtualizado);
            return clienteMAPPER.map(clienteSalvo);
        }

        return null;
    }

    //Deletar Cliente

    public void deletarCliente(Long id){
        Optional<ClienteModel> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()){
            clienteRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Cliente com ID " + id + " não encontrado.");
        }

    }
}

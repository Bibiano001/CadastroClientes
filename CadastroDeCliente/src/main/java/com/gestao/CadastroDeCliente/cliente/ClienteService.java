package com.gestao.CadastroDeCliente.cliente;

import com.gestao.CadastroDeCliente.categoria.CategoriaService;
import com.gestao.CadastroDeCliente.exception.RecursoNaoEncontradoException;
import com.gestao.CadastroDeCliente.exception.RegraDeNegocioException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final CategoriaService categoriaService;

    public ClienteService(ClienteRepository clienteRepository,
                          ClienteMapper clienteMapper,
                          CategoriaService categoriaService) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.categoriaService = categoriaService;
    }

    // Listar todos os clientes
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::map)
                .toList();
    }

    // Buscar cliente por ID
    public ClienteDTO buscarClientePorId(Long id) {
        return clienteMapper.map(buscarModelPorId(id));
    }

    // Criar cliente
    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new RegraDeNegocioException("Já existe um cliente com o e-mail " + clienteDTO.getEmail() + ".");
        }
        ClienteModel clienteModel = clienteMapper.map(clienteDTO);
        clienteModel.setId(null); // o ID é sempre gerado pelo banco
        vincularCategoria(clienteModel, clienteDTO.getCategoriaId());
        ClienteModel clienteSalvo = clienteRepository.save(clienteModel);
        return clienteMapper.map(clienteSalvo);
    }

    // Atualizar cliente
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        ClienteModel clienteExistente = buscarModelPorId(id);
        if (clienteRepository.existsByEmailAndIdNot(clienteDTO.getEmail(), id)) {
            throw new RegraDeNegocioException("Já existe outro cliente com o e-mail " + clienteDTO.getEmail() + ".");
        }
        clienteExistente.setNome(clienteDTO.getNome());
        clienteExistente.setEmail(clienteDTO.getEmail());
        clienteExistente.setIdade(clienteDTO.getIdade());
        vincularCategoria(clienteExistente, clienteDTO.getCategoriaId());
        ClienteModel clienteSalvo = clienteRepository.save(clienteExistente);
        return clienteMapper.map(clienteSalvo);
    }

    // Deletar cliente
    public void deletarCliente(Long id) {
        ClienteModel cliente = buscarModelPorId(id);
        clienteRepository.delete(cliente);
    }

    private ClienteModel buscarModelPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com ID " + id + " não encontrado."));
    }

    private void vincularCategoria(ClienteModel cliente, Long categoriaId) {
        if (categoriaId == null) {
            cliente.setCategoria(null);
        } else {
            cliente.setCategoria(categoriaService.buscarModelPorId(categoriaId));
        }
    }
}

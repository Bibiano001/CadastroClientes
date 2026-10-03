package com.gestao.CadastroDeCliente.categoria;

import com.gestao.CadastroDeCliente.cliente.ClienteRepository;
import com.gestao.CadastroDeCliente.exception.RecursoNaoEncontradoException;
import com.gestao.CadastroDeCliente.exception.RegraDeNegocioException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ClienteRepository clienteRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository,
                            ClienteRepository clienteRepository,
                            CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.clienteRepository = clienteRepository;
        this.categoriaMapper = categoriaMapper;
    }

    // Listar todas as categorias
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::map)
                .toList();
    }

    // Buscar categoria por ID
    public CategoriaDTO buscarCategoriaPorId(Long id) {
        return categoriaMapper.map(buscarModelPorId(id));
    }

    // Criar uma categoria
    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO) {
        CategoriaModel categoriaModel = categoriaMapper.map(categoriaDTO);
        categoriaModel.setId(null); // o ID é sempre gerado pelo banco
        CategoriaModel categoriaSalva = categoriaRepository.save(categoriaModel);
        return categoriaMapper.map(categoriaSalva);
    }

    // Atualizar uma categoria
    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        CategoriaModel categoriaExistente = buscarModelPorId(id);
        categoriaExistente.setTipo(categoriaDTO.getTipo());
        CategoriaModel categoriaSalva = categoriaRepository.save(categoriaExistente);
        return categoriaMapper.map(categoriaSalva);
    }

    // Deletar uma categoria por ID
    public void deletarCategoria(Long id) {
        CategoriaModel categoria = buscarModelPorId(id);
        if (clienteRepository.existsByCategoriaId(id)) {
            throw new RegraDeNegocioException(
                    "A categoria " + id + " possui clientes vinculados e não pode ser deletada.");
        }
        categoriaRepository.delete(categoria);
    }

    // Usado também pelo ClienteService para vincular a categoria ao cliente
    public CategoriaModel buscarModelPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com ID " + id + " não encontrada."));
    }
}

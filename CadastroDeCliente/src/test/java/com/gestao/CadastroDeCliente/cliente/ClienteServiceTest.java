package com.gestao.CadastroDeCliente.cliente;

import com.gestao.CadastroDeCliente.categoria.CategoriaModel;
import com.gestao.CadastroDeCliente.categoria.CategoriaService;
import com.gestao.CadastroDeCliente.exception.RecursoNaoEncontradoException;
import com.gestao.CadastroDeCliente.exception.RegraDeNegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CategoriaService categoriaService;

    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        clienteService = new ClienteService(clienteRepository, new ClienteMapper(), categoriaService);
    }

    @Test
    void deveListarClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente(1L, "ana@email.com")));

        List<ClienteDTO> clientes = clienteService.listarClientes();

        assertThat(clientes).hasSize(1);
        assertThat(clientes.get(0).getEmail()).isEqualTo("ana@email.com");
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExiste() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.buscarClientePorId(99L))
                .isInstanceOf(RecursoNaoEncontradoException.class);
    }

    @Test
    void deveCriarClienteComCategoria() {
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(10L);
        when(clienteRepository.existsByEmail("ana@email.com")).thenReturn(false);
        when(categoriaService.buscarModelPorId(10L)).thenReturn(categoria);
        when(clienteRepository.save(any(ClienteModel.class))).thenAnswer(invocacao -> {
            ClienteModel salvo = invocacao.getArgument(0);
            salvo.setId(1L);
            return salvo;
        });

        ClienteDTO criado = clienteService.criarCliente(new ClienteDTO(null, "Ana", "ana@email.com", 25, 10L));

        assertThat(criado.getId()).isEqualTo(1L);
        assertThat(criado.getCategoriaId()).isEqualTo(10L);
    }

    @Test
    void naoDeveCriarClienteComEmailDuplicado() {
        when(clienteRepository.existsByEmail("ana@email.com")).thenReturn(true);

        assertThatThrownBy(() -> clienteService.criarCliente(new ClienteDTO(null, "Ana", "ana@email.com", 25, null)))
                .isInstanceOf(RegraDeNegocioException.class);
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void deveAtualizarCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente(1L, "ana@email.com")));
        when(clienteRepository.existsByEmailAndIdNot("nova@email.com", 1L)).thenReturn(false);
        when(clienteRepository.save(any(ClienteModel.class))).thenAnswer(invocacao -> invocacao.getArgument(0));

        ClienteDTO atualizado = clienteService.atualizarCliente(1L, new ClienteDTO(null, "Ana Maria", "nova@email.com", 26, null));

        assertThat(atualizado.getNome()).isEqualTo("Ana Maria");
        assertThat(atualizado.getEmail()).isEqualTo("nova@email.com");
    }

    @Test
    void naoDeveDeletarClienteInexistente() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.deletarCliente(99L))
                .isInstanceOf(RecursoNaoEncontradoException.class);
        verify(clienteRepository, never()).delete(any());
    }

    private ClienteModel cliente(Long id, String email) {
        return new ClienteModel(id, "Ana", email, 25, null);
    }
}

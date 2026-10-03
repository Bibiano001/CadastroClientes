package com.gestao.CadastroDeCliente.categoria;

import com.gestao.CadastroDeCliente.cliente.ClienteRepository;
import com.gestao.CadastroDeCliente.exception.RecursoNaoEncontradoException;
import com.gestao.CadastroDeCliente.exception.RegraDeNegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    private CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        categoriaService = new CategoriaService(categoriaRepository, clienteRepository, new CategoriaMapper());
    }

    @Test
    void naoDeveDeletarCategoriaComClientesVinculados() {
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(1L);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(clienteRepository.existsByCategoriaId(1L)).thenReturn(true);

        assertThatThrownBy(() -> categoriaService.deletarCategoria(1L))
                .isInstanceOf(RegraDeNegocioException.class);
        verify(categoriaRepository, never()).delete(any());
    }

    @Test
    void deveDeletarCategoriaSemClientes() {
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(1L);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(clienteRepository.existsByCategoriaId(1L)).thenReturn(false);

        categoriaService.deletarCategoria(1L);

        verify(categoriaRepository).delete(categoria);
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExiste() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoriaService.buscarCategoriaPorId(99L))
                .isInstanceOf(RecursoNaoEncontradoException.class);
    }
}

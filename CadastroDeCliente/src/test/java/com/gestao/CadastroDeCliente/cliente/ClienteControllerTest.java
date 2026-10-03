package com.gestao.CadastroDeCliente.cliente;

import com.gestao.CadastroDeCliente.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Test
    void deveRetornar404QuandoClienteNaoExiste() throws Exception {
        when(clienteService.buscarClientePorId(99L))
                .thenThrow(new RecursoNaoEncontradoException("Cliente com ID 99 não encontrado."));

        mockMvc.perform(get("/clientes/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem").value("Cliente com ID 99 não encontrado."));
    }

    @Test
    void deveRetornar400QuandoDadosSaoInvalidos() throws Exception {
        String json = """
                { "nome": "", "email": "email-invalido", "idade": -1 }
                """;

        mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.campos.nome").exists())
                .andExpect(jsonPath("$.campos.email").exists())
                .andExpect(jsonPath("$.campos.idade").exists());
    }
}

package com.gestao.CadastroDeCliente.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroResponse(
        LocalDateTime timestamp,
        int status,
        String mensagem,
        Map<String, String> campos
) {
}

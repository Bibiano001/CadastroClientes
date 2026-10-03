package com.gestao.CadastroDeCliente.Cliente;

import com.gestao.CadastroDeCliente.Categoria.CategoriaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private int idade;
    private CategoriaModel categoria;

}

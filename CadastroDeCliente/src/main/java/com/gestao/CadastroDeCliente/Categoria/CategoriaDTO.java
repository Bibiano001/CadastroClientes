package com.gestao.CadastroDeCliente.Categoria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestao.CadastroDeCliente.Cliente.ClienteModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoriaDTO {

    private Long id;
    private String tipo;
    private List<ClienteModel> clientes;

}

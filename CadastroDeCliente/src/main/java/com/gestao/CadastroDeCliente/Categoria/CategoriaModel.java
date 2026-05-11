package com.gestao.CadastroDeCliente.Categoria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestao.CadastroDeCliente.Cliente.ClienteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_categorias")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CategoriaModel {

    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    private String tipo;


    @OneToMany(mappedBy = "categorias", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ClienteModel> clientes;


}

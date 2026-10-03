package com.gestao.CadastroDeCliente.categoria;

import com.gestao.CadastroDeCliente.cliente.ClienteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    // Sem cascade: apagar uma categoria NÃO pode apagar os clientes dela
    @OneToMany(mappedBy = "categoria")
    private List<ClienteModel> clientes = new ArrayList<>();
}

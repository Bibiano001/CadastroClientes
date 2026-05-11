package com.gestao.CadastroDeCliente.Cliente;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_clientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ClienteModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "idade")
    private int idade;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categorias;




}

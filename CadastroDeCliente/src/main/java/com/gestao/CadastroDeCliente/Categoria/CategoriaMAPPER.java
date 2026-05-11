package com.gestao.CadastroDeCliente.Categoria;


import org.springframework.stereotype.Component;

@Component
public class CategoriaMAPPER {

    public CategoriaModel map(CategoriaDTO categoriaDTO){

        CategoriaModel categoriaModel = new CategoriaModel();
        categoriaModel.setId(categoriaDTO.getId());
        categoriaModel.setTipo(categoriaDTO.getTipo());
        categoriaModel.setClientes(categoriaDTO.getClientes());

        return categoriaModel;
    }

    public CategoriaDTO map(CategoriaModel categoriaModel){

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoriaModel.getId());
        categoriaDTO.setTipo(categoriaModel.getTipo());
        categoriaDTO.setClientes(categoriaModel.getClientes());

        return  categoriaDTO;
    }
}

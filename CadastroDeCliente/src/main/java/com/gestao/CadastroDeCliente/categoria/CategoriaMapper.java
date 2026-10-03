package com.gestao.CadastroDeCliente.categoria;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaModel map(CategoriaDTO categoriaDTO) {
        CategoriaModel categoriaModel = new CategoriaModel();
        categoriaModel.setId(categoriaDTO.getId());
        categoriaModel.setTipo(categoriaDTO.getTipo());
        return categoriaModel;
    }

    public CategoriaDTO map(CategoriaModel categoriaModel) {
        return new CategoriaDTO(categoriaModel.getId(), categoriaModel.getTipo());
    }
}

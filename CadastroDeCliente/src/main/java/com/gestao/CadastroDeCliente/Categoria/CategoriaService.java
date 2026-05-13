package com.gestao.CadastroDeCliente.Categoria;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;
    private CategoriaMAPPER categoriaMAPPER;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMAPPER categoriaMAPPER) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMAPPER = categoriaMAPPER;
    }

    // listar todas as categorias

    public List<CategoriaDTO> listarCategorias (){
        List<CategoriaModel> categoriaModels = categoriaRepository.findAll();
        return categoriaModels.stream()
                .map(categoriaMAPPER::map)
                .collect(Collectors.toList());
    }

    // Criar uma categoria

    public CategoriaDTO addCategoria(CategoriaDTO categoriaDTO){
        CategoriaModel categoriaModel = categoriaMAPPER.map(categoriaDTO);
        CategoriaModel categoriaSalvada = categoriaRepository.save(categoriaModel);
        return  categoriaMAPPER.map(categoriaSalvada);
    }
}

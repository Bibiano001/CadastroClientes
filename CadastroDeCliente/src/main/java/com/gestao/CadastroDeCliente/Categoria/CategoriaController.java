package com.gestao.CadastroDeCliente.Categoria;


import com.gestao.CadastroDeCliente.Cliente.ClienteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarCategorias (){
        List<CategoriaDTO> categoriaDTOS = categoriaService.listarCategorias();
        if (categoriaDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista está vazia.");
        }
        else {
            return ResponseEntity.status(HttpStatus.FOUND).body(categoriaDTOS);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CategoriaDTO> addCategoria (@RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO categoriaAdicionada = categoriaService.addCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaAdicionada);
    }


}

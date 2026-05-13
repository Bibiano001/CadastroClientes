package com.gestao.CadastroDeCliente.Categoria;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

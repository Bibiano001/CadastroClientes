package com.gestao.CadastroDeCliente.Categoria;


import com.gestao.CadastroDeCliente.Cliente.ClienteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarCategoriaID(@PathVariable Long id){
        CategoriaDTO categoriaDTO = categoriaService.listarCaterogiaID(id);
        if (categoriaDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }
        else {
            return ResponseEntity.status(HttpStatus.FOUND).body(categoriaDTO);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<CategoriaDTO> addCategoria (@RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO categoriaAdicionada = categoriaService.addCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaAdicionada);
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarCategoria(@PathVariable Long id,@RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO categoriaID = categoriaService.listarCaterogiaID(id);
        if (categoriaID != null){
            CategoriaDTO categoriaAtualizada = categoriaService.atualizarCategoria(id , categoriaDTO);
            return ResponseEntity.ok(categoriaAtualizada);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }


    }


}

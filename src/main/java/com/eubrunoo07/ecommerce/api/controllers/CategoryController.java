package com.eubrunoo07.ecommerce.api.controllers;

import com.eubrunoo07.ecommerce.api.dtos.request.CategoryRequestDTO;
import com.eubrunoo07.ecommerce.api.entities.Category;
import com.eubrunoo07.ecommerce.api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/categories")
@CrossOrigin(originPatterns = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody@Valid CategoryRequestDTO dto){
        //Criando instância de Categoria
        Category category = new Category();
        //Copiando informações do DTO para minha Entidade
        BeanUtils.copyProperties(dto, category);
        //Por fim salvando e colocando o STATUS 201
        categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public List<Category> categories(){
        //Retornar todas as categorias do banco
        return categoryService.findAll();
    }

}

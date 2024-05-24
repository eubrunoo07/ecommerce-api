package com.eubrunoo07.ecommerce.api.controllers;

import com.eubrunoo07.ecommerce.api.dtos.request.ProductFilterDTO;
import com.eubrunoo07.ecommerce.api.dtos.request.ProductRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.response.ProductResponseDTO;
import com.eubrunoo07.ecommerce.api.dtos.response.ProductReviewResponseDTO;
import com.eubrunoo07.ecommerce.api.entities.Category;
import com.eubrunoo07.ecommerce.api.entities.Product;
import com.eubrunoo07.ecommerce.api.entities.ProductReview;
import com.eubrunoo07.ecommerce.api.entities.User;
import com.eubrunoo07.ecommerce.api.services.CategoryService;
import com.eubrunoo07.ecommerce.api.services.ProductService;
import com.eubrunoo07.ecommerce.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/products")
@CrossOrigin(originPatterns = "*")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody@Valid ProductRequestDTO dto){
        //Verificando se categoria existe
        Category category = categoryService
                .findById(dto.getCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        //Verificando se user existe
        User user = userService
                .findById(dto.getUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        //Criando o Produto
        Product product = new Product();

        //Copiando informações passadas no DTO para a Entidade
        BeanUtils.copyProperties(dto, product);
        product.setCategory(category);
        product.setUser(user);

        //Por fim salvando e retornando sucesso
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public List<ProductResponseDTO> products(){
        // Recupera todos os produtos disponíveis
        List<Product> products = productService.findAll();

        // Inicializa uma lista para armazenar os DTOs de resposta dos produtos
        List<ProductResponseDTO> dtos = new ArrayList<>();

        // Itera sobre os produtos para criar os DTOs correspondentes
        for (Product product : products) {

            // Inicializa uma lista para armazenar os DTOs de resposta das avaliações
            List<ProductReviewResponseDTO> reviewDTOs = new ArrayList<>();

            // Itera sobre as avaliações do produto
            for (ProductReview review : product.getReviews()) {
                // Cria um DTO para cada avaliação do produto
                ProductReviewResponseDTO reviewDTO = ProductReviewResponseDTO.builder()
                        .id(review.getId())
                        .review(review.getReview())
                        .comments(review.getComments())
                        .userName(review.getUser().getName())
                        .build();

                // Adiciona o DTO à lista de DTOs de resposta das avaliações
                reviewDTOs.add(reviewDTO);
            }


            // Cria um DTO para cada produto com os detalhes relevantes
            ProductResponseDTO dto = ProductResponseDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .category(product.getCategory().getName())
                    .description(product.getDescription())
                    .user(product.getUser().getName())
                    .quantityInStock(product.getQuantityInStock())
                    .images(product.getImages())
                    .reviews(reviewDTOs)
                    .build();

            // Adiciona o DTO à lista de DTOs de resposta dos produtos
            dtos.add(dto);
        }

        // Retorna a lista de DTOs de resposta dos produtos
        return dtos;
    }

    @GetMapping("/filter")
    public List<ProductResponseDTO> filter(ProductFilterDTO dto){
        // Cria um objeto Product a partir do DTO de filtro
        Product exampleProduct = new Product();
        BeanUtils.copyProperties(dto, exampleProduct);

        // Configura o matcher para correspondência do exemplo
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        // Cria um exemplo com base no objeto Product e no matcher configurado
        Example<Product> example = Example.of(exampleProduct, matcher);

        // Recupera os produtos que correspondem ao exemplo fornecido
        List<Product> products = productService.findAllExample(example);

        // Inicializa uma lista para armazenar os DTOs de resposta dos produtos filtrados
        List<ProductResponseDTO> response = new ArrayList<>();

        // Itera sobre os produtos filtrados para criar os DTOs correspondentes
        for (Product product : products) {
            // Cria um DTO para cada produto filtrado com os detalhes relevantes
            response.add(ProductResponseDTO.builder()
                    .id(product.getId()) // ID do produto
                    .name(product.getName()) // Nome do produto
                    .price(product.getPrice()) // Preço do produto
                    .category(product.getCategory().getName()) // Categoria do produto
                    .description(product.getDescription()) // Descrição do produto
                    .user(product.getUser().getName()) // Nome do usuário vendedor
                    .build());
        }

        // Retorna a lista de DTOs de resposta dos produtos filtrados
        return response;
    }

}

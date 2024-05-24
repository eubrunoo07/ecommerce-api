package com.eubrunoo07.ecommerce.api.controllers;

import com.eubrunoo07.ecommerce.api.dtos.request.ProductReviewRequestDTO;
import com.eubrunoo07.ecommerce.api.entities.Product;
import com.eubrunoo07.ecommerce.api.entities.ProductReview;
import com.eubrunoo07.ecommerce.api.entities.User;
import com.eubrunoo07.ecommerce.api.services.ProductReviewService;
import com.eubrunoo07.ecommerce.api.services.ProductService;
import com.eubrunoo07.ecommerce.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ecommerce/api/reviews")
@CrossOrigin(originPatterns = "*")
public class ProductReviewController {

    @Autowired
    private ProductReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> createReview(@RequestBody@Valid ProductReviewRequestDTO dto){

        //Verificando se User existe
        User user = userService
                .findById(dto.getUser()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        //Verificando se Product existe
        Product product = productService
                .findById(dto.getProduct()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));


        //Criando entidade e colocando devidas informações
        ProductReview review = new ProductReview();
        BeanUtils.copyProperties(dto, review);
        review.setProduct(product);
        review.setUser(user);

        //Setando review no produto selecionado
        product.getReviews().add(review);
        productService.save(product);

        //Por fim, salvamos o review
        reviewService.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

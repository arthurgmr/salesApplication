package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.domain.entity.Product;
import io.github.arthurgmr.domain.repository.IProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private IProductRepository productRepository;

    public ProductController(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product dataProduct) {
        return productRepository.save(dataProduct);
    }




}

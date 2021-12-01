package io.github.arthurgmr.rest.controller;

import io.github.arthurgmr.domain.entity.Product;
import io.github.arthurgmr.domain.repository.IProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private IProductRepository productRepository;

    public ProductController(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public Product saveProduct(@RequestBody Product dataProduct) {
        return productRepository.save(dataProduct);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById (@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Product not found!");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProduct(@PathVariable Integer id, @RequestBody Product productUpdated) {
        productRepository
                .findById(id)
                .map(productExists -> {
                    productUpdated.setId(productExists.getId());
                    productRepository.save(productUpdated);
                    return productExists;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found!"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduct (@PathVariable Integer id) {
        productRepository
                .findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return product;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
    }

    @GetMapping("/find")
    public List<Product> findProduct (Product filter) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);

        return productRepository.findAll(example);
    }
}

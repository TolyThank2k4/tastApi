package com.ra.taskapi.controller;

import com.ra.taskapi.model.entity.Product;
import com.ra.taskapi.model.entity.dto.DataError;
import com.ra.taskapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> index() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> add(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(new DataError("product not found", 404), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Product product) {
        if (productService.findById(id) != null) {
            product.setId(id);
            Product productUpdate = productService.save(product);
            return new ResponseEntity<>(productUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(new DataError("product not found", 404), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.delete(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DataError("product not found", 404), HttpStatus.NOT_FOUND);
    }
}

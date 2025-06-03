package com.ra.taskapi.controller;

import com.ra.taskapi.model.entity.Category;
import com.ra.taskapi.model.entity.dto.DataError;
import com.ra.taskapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> index() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(new DataError("category not found", 404), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Category category) {
        if (categoryService.findById(id) != null) {
            category.setId(id);
            Category categoryUpdate = categoryService.save(category);
            return new ResponseEntity<>(categoryUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(new DataError("category not found", 404), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            categoryService.delete(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DataError("category not found", 404), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable int id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            category.setStatus(!category.isStatus());
            categoryService.save(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(new DataError("category not found", 404), HttpStatus.NOT_FOUND);
    }

}

package com.ra.taskapi.service;

import com.ra.taskapi.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product save (Product product);
    Product findById(int id );
    void delete(int id);
}

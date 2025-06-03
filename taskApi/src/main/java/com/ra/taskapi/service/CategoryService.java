package com.ra.taskapi.service;

import com.ra.taskapi.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save (Category category);
    Category findById(int id );
    void delete(int id);
}

package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.model.Category;
import com.basovProjects.wokBar.repository.CategoryRepository;
import com.basovProjects.wokBar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService<Long, Category> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public boolean save(Category category) {
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean update(Category category) {
        categoryRepository.saveAndFlush(category);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryFromDB = categoryRepository.findById(id);
        return categoryFromDB.orElse(null);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}

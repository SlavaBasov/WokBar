package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.model.category.CategoryTranslate;
import com.basovProjects.wokBar.repository.CategoryRepository;
import com.basovProjects.wokBar.repository.CategoryTranslateRepository;
import com.basovProjects.wokBar.repository.LanguageRepository;
import com.basovProjects.wokBar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService<Long, Category> {

    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;
    private final CategoryTranslateRepository categoryTranslateRepository;
    private final Locale defaultLocale;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               LanguageRepository languageRepository,
                               CategoryTranslateRepository categoryTranslateRepository,
                               @Qualifier("defaultLocale") Locale defaultLocale) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
        this.categoryTranslateRepository = categoryTranslateRepository;
        this.defaultLocale = defaultLocale;
    }

    @Override
    public boolean save(Category category) {
//        try {
            categoryRepository.save(category);
//        } catch (DataIntegrityViolationException ex){
//            System.out.println("уже есть такая категория");
//        }

        return true;
    }

    @Override
    @Transactional
    public boolean update(Category category) {
        Category firstCategory = findById(category.getId());
        if(firstCategory.getId() != 0L){
            firstCategory.setName(category.getName());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(new Category());
    }

    @Override
    public Category findByIdWithLocalization(Long id) {
        Category category = findById(id);
        Locale locale = LocaleContextHolder.getLocale();
        if(!locale.toString().equals(defaultLocale.toString())){
            CategoryTranslate categoryTranslate = categoryTranslateRepository.findByLanguage_IdAndCategory_Id(locale.toString(), id).orElse(new CategoryTranslate());
            if(category.getId().equals(categoryTranslate.getCategory().getId())){
                category.setName(categoryTranslate.getName());
            }
        }
         return category;
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        Locale locale = LocaleContextHolder.getLocale();

        if(!locale.toString().equals(defaultLocale.toString())) {
            for (Category category : categoryList) {
                for (CategoryTranslate categoryTranslate : findAllCategoriesByLocale(locale)) {
                    if (category.getId().equals(categoryTranslate.getCategory().getId())) {
                        category.setName(categoryTranslate.getName());
                    }
                }
            }
        }

        return categoryList;
    }

    public List<CategoryTranslate> findAllCategoriesByLocale(Locale locale) {
        return categoryTranslateRepository.findAllByLanguage_Id(locale.toString());
    }



}

package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Language;
import com.basovProjects.wokBar.model.Product;
import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.model.category.CategoryTranslate;
import com.basovProjects.wokBar.repository.CategoryRepository;
import com.basovProjects.wokBar.repository.CategoryTranslateRepository;
import com.basovProjects.wokBar.repository.LanguageRepository;
import com.basovProjects.wokBar.service.CategoryService;
import com.basovProjects.wokBar.service.CategoryTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService<Long, Category> {

    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;
    private final CategoryTranslateRepository categoryTranslateRepository;
    private final CategoryTranslateService<Long, CategoryTranslate> categoryTranslateService;
    private final Locale defaultLocale;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               LanguageRepository languageRepository,
                               CategoryTranslateRepository categoryTranslateRepository,
                               CategoryTranslateService<Long, CategoryTranslate> categoryTranslateService,
                               @Qualifier("defaultLocale") Locale defaultLocale) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
        this.categoryTranslateRepository = categoryTranslateRepository;
        this.categoryTranslateService = categoryTranslateService;
        this.defaultLocale = defaultLocale;
    }

    @Override
    @Transactional
    public boolean save(Category category) {
        if(isNameOfCategoryExist(category.getName())){
            return false;
        }
        categoryRepository.save(category);
        categoryTranslateService.saveAllLocales(category);
        return true;
    }

    private boolean isNameOfCategoryExist(String name){
        Category category = categoryRepository.findByName(name);
        return category != null;
    }

    @Override
    @Transactional
    public boolean update(Category category) throws MyObjectNotFoundException {
        Category firstCategory = findById(category.getId());
        if(!firstCategory.getName().equals(category.getName()) && isNameOfCategoryExist(category.getName())){
            return false;
        }
        if (firstCategory.getId() != 0L) {
            firstCategory.setName(category.getName());
            CategoryTranslate categoryTranslateFound = categoryTranslateService
                    .findByCategoryIdAndLanguageId(category.getId(), defaultLocale.toString());
            categoryTranslateFound.setName(category.getName());
            return true;
        }
        return false;
    }

    @Override
    public Category findById(Long id) throws MyObjectNotFoundException {
        Category category;
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            category = categoryOptional.get();
        } else {
            throw new MyObjectNotFoundException("Method not found category by id " + id);
        }
        return category;
    }

    @Override
    public Category findByIdWithLocalization(Long id) throws MyObjectNotFoundException {
        Category category = findById(id);
        Locale locale = LocaleContextHolder.getLocale();
        if (!locale.toString().equals(defaultLocale.toString())) {
            CategoryTranslate categoryTranslate = categoryTranslateRepository.findByLanguage_IdAndCategory_Id(locale.toString(), id).orElse(new CategoryTranslate());
            if (category.getId().equals(categoryTranslate.getCategory().getId())) {
                category.setName(categoryTranslate.getName());
            }
        }
        return category;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllCategoriesWithLocalization() {
        List<Category> categoryList = findAllCategories();
        Locale locale = LocaleContextHolder.getLocale();

        if (!locale.toString().equals(defaultLocale.toString())) {
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
        return categoryTranslateService.findAllByLanguageId(locale.toString());
    }

    @Override
    @Transactional
    public boolean delete(Long id){
        categoryTranslateService.deleteAllByCategoryId(id);
        categoryRepository.deleteById(id);
        return true;
    }


}

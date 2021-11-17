package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.model.Category;
import com.basovProjects.wokBar.model.CategoryTranslate;
import com.basovProjects.wokBar.model.Language;
import com.basovProjects.wokBar.repository.CategoryRepository;
import com.basovProjects.wokBar.repository.CategoryTranslateRepository;
import com.basovProjects.wokBar.repository.LanguageRepository;
import com.basovProjects.wokBar.service.CategoryService;
import com.mysql.cj.xdevapi.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService<Long, Category> {

    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;
    private final CategoryTranslateRepository categoryTranslateRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, LanguageRepository languageRepository, CategoryTranslateRepository categoryTranslateRepository) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
        this.categoryTranslateRepository = categoryTranslateRepository;
    }

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
    @Transactional
    public List<Category> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        Locale locale = LocaleContextHolder.getLocale();
        Language language = languageRepository.getById(locale.toString());

        if(!language.isDefaultLocal()) {
            List<CategoryTranslate> categoryTranslateList = findAllCategoriesByLocale(locale);
            return getTranslatedAllCategories(categoryList, categoryTranslateList, locale);
        }

        return categoryList;
    }

    private List<CategoryTranslate> findAllCategoriesByLocale(Locale locale) {
        return categoryTranslateRepository.findAllByLanguage_Id(locale.toString());
    }

    private List<Category> getTranslatedAllCategories(List<Category> categoryList, List<CategoryTranslate> categoryTranslateList, Locale locale){


//        List<Category> resultCategoryList = List.copyOf(categoryList);
//        List<CategoryTranslate> resultCategoryTranslateList = categoryTranslateList.stream().collect(Collectors.toList());
//        List<CategoryTranslate> resultCategoryTranslateList = List.copyOf(categoryTranslateList);
        List<Category> resultCategoryList = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++){
            resultCategoryList.add(new Category());
        }
        Collections.copy(resultCategoryList,categoryList);

        List<CategoryTranslate> resultCategoryTranslateList = new ArrayList<>();
        for (int i = 0; i < categoryTranslateList.size(); i++){
            resultCategoryTranslateList.add(new CategoryTranslate());
        }
        Collections.copy(resultCategoryTranslateList,categoryTranslateList);

        for (Category category : resultCategoryList) {
            for (CategoryTranslate categoryTranslate : resultCategoryTranslateList) {
                if (category.getId().equals(categoryTranslate.getCategory().getId())) {
                    category.setName(categoryTranslate.getName());
                }
            }
        }
        return resultCategoryList;
    }



//    @Override
//    public List<Category> findAllCategories() {
//        List<Category> categoryList = categoryRepository.findAll();
//
//        Locale locale = LocaleContextHolder.getLocale();
//        if(!locale.toString().equals("en")) {
//            List<CategoryTranslate> categoryTranslateList = findAllCategoriesByLocale();
//
//            for (Category category : categoryList) {
//                for (CategoryTranslate categoryTranslate : categoryTranslateList) {
//                    if (category.getId().equals(categoryTranslate.getCategory().getId())) {
//                        category.setName(categoryTranslate.getName());
//                    }
//                }
//            }
//        }
//
//        return categoryList;
//    }
//
//    public List<CategoryTranslate> findAllCategoriesByLocale() {
//        Locale locale = LocaleContextHolder.getLocale();
//        List<CategoryTranslate> categoryTranslateList = categoryTranslateRepository.findAllByLanguage_Id(locale.toString());
//        return categoryTranslateList;
//    }



}

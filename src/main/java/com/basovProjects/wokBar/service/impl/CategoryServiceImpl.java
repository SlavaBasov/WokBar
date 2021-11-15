package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.model.Category;
import com.basovProjects.wokBar.model.CategoryTranslate;
import com.basovProjects.wokBar.model.Language;
import com.basovProjects.wokBar.repository.CategoryRepository;
import com.basovProjects.wokBar.repository.CategoryTranslateRepository;
import com.basovProjects.wokBar.repository.LanguageRepository;
import com.basovProjects.wokBar.service.CategoryService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.repository.support.EntityManagerBeanDefinitionRegistrarPostProcessor;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

//    @Autowired
//    public CategoryServiceImpl(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }

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
//        Locale locale = LocaleContextHolder.getLocale();
//        System.out.println(locale.toString());
        Optional<Category> categoryFromDB = categoryRepository.findById(id);
        return categoryFromDB.orElse(null);
    }

    @Override
    @Transactional
    public List<Category> findAllCategories() {
        List<Category> categoryList = new ArrayList<>(categoryRepository.findAll());

//
        Locale locale = LocaleContextHolder.getLocale();
        Language language = languageRepository.getById(locale.toString());
//
        if(!language.isDefaultLocal()) {
            List<CategoryTranslate> categoryTranslateList = new ArrayList<>(categoryTranslateRepository.findAllByLanguage(language));
            for (Category category : categoryList) {
                for (CategoryTranslate categoryTranslate : categoryTranslateList) {
                    if (category.getId().equals(categoryTranslate.getCategory().getId())) {
                        category.setName(categoryTranslate.getName());
                    }
                }
            }
        }
        return categoryList;
    }
//    @Override
//    public List<CategoryTranslate> findCategories() {
////        Locale locale = LocaleContextHolder.getLocale();
////        System.out.println("#########################################################################");
////        System.out.println(locale.toString());
////        System.out.println("#########################################################################");
//        Locale locale = LocaleContextHolder.getLocale();
//        return categoryTranslateRepository.findAllByLanguage(languageRepository.getById(locale.toString()));
////        return categoryRepository.findAll();
//    }
}

package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Language;
import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.model.category.CategoryTranslate;
import com.basovProjects.wokBar.repository.CategoryTranslateRepository;
import com.basovProjects.wokBar.repository.LanguageRepository;
import com.basovProjects.wokBar.service.CategoryTranslateService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("categoryTranslateService")
public class CategoryTranslateServiceImpl implements CategoryTranslateService<Long, CategoryTranslate> {

    private final CategoryTranslateRepository categoryTranslateRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public CategoryTranslateServiceImpl(CategoryTranslateRepository categoryTranslateRepository,
                                        @Qualifier("defaultLocale") Locale defaultLocale,
                                        LanguageRepository languageRepository) {
        this.categoryTranslateRepository = categoryTranslateRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public boolean save(Category category, Language language) {
        CategoryTranslate categoryTranslate = categoryToCategoryTranslate(category, language);
        categoryTranslateRepository.save(categoryTranslate);
        return true;
    }

    @Override
    public boolean saveAllLocales(Category category) {
        for (Language language : languageRepository.findAll()) {
            categoryTranslateRepository.save(categoryToCategoryTranslate(category, language));
        }
        return true;
    }

    private CategoryTranslate categoryToCategoryTranslate(Category category, Language language) {
        return new CategoryTranslate(category, language, category.getName());
    }

    @Override
    @Transactional
    public boolean update(CategoryTranslate categoryTranslate) throws MyObjectNotFoundException {
        CategoryTranslate foundCategoryTranslate = findByCategoryIdAndLanguageId(
                categoryTranslate.getCategory().getId(), categoryTranslate.getLanguage().getId());
        foundCategoryTranslate.setName(categoryTranslate.getName());
        return true;
    }

    @Override
    public CategoryTranslate findByCategoryIdAndLanguageId(Long categoryId, String languageId) throws MyObjectNotFoundException {
        CategoryTranslate foundCategoryTranslate;
        Optional<CategoryTranslate> categoryTranslateOptional = categoryTranslateRepository
                .findByLanguage_IdAndCategory_Id(languageId, categoryId);
        if (categoryTranslateOptional.isPresent()) {
            foundCategoryTranslate = categoryTranslateOptional.get();
        } else {
            throw new MyObjectNotFoundException("Method not found translate by category id " +
                    categoryId + " and language id " +
                    languageId);
        }
        return foundCategoryTranslate;
    }

    @Override
    public List<CategoryTranslate> findAllByLanguageId(String languageId) {
        return categoryTranslateRepository.findAllByLanguage_Id(languageId);
    }

    public List<CategoryTranslate> findAllByCategoryId(Long categoryId) {
        return categoryTranslateRepository.findAllByCategory_Id(categoryId);
    }

    @Override
    public boolean delete(Long id) {
        categoryTranslateRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAllByCategoryId(Long categoryId) {
        List<CategoryTranslate> categoryTranslateList = findAllByCategoryId(categoryId);
        for(CategoryTranslate categoryTranslate: categoryTranslateList){
            delete(categoryTranslate.getId());
        }
        return true;
    }

}

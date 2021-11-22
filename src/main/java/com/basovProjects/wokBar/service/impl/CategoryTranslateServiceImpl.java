package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.model.Language;
import com.basovProjects.wokBar.model.category.CategoryTranslate;
import com.basovProjects.wokBar.repository.CategoryTranslateRepository;
import com.basovProjects.wokBar.repository.LanguageRepository;
import com.basovProjects.wokBar.service.CategoryTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service("categoryTranslateService")
public class CategoryTranslateServiceImpl implements CategoryTranslateService<Long, CategoryTranslate> {

    private final CategoryTranslateRepository categoryTranslateRepository;
    private final Locale defaultLocale;
    private final LanguageRepository languageRepository;
    private final Language russianLanguage;


    @Autowired
    public CategoryTranslateServiceImpl(CategoryTranslateRepository categoryTranslateRepository,
                                        @Qualifier("defaultLocale") Locale defaultLocale,
                                        LanguageRepository languageRepository) {
        this.categoryTranslateRepository = categoryTranslateRepository;
        this.defaultLocale = defaultLocale;
        this.languageRepository = languageRepository;
        this.russianLanguage = languageRepository.findById("ru").orElse(new Language());
    }

    @Override
    public CategoryTranslate findTranslationToRussianByCategoryId(Long categoryId) {
        return categoryTranslateRepository.findByLanguage_IdAndCategory_Id(russianLanguage.getId(), categoryId).orElse(new CategoryTranslate());
    }

//    @Override
//    public boolean save(CategoryTranslate categoryTranslate) {
//        categoryTranslateRepository.save(categoryTranslate);
//        return true;
//    }
//
    @Override
    public boolean update(CategoryTranslate categoryTranslate) {
        categoryTranslateRepository.saveAndFlush(categoryTranslate);
        return true;
    }
//
//    @Override
//    public boolean delete(Long id) {
//        categoryTranslateRepository.deleteById(id);
//        return true;
//    }

//    @Override
//    public List<CategoryTranslate> findAllByCategoryId(Long id) {
//        return categoryTranslateRepository.findAllByCategory_Id(id);
//    }
//
//    @Override
//    public List<CategoryTranslate> findAllByCategoryIdWithoutEnLocal(Long id) {
//        List<CategoryTranslate> categoryTranslateList = findAllByCategoryId(id);
//        return categoryTranslateList
//                .stream()
//                .filter(i->i.getLanguage().getName()!=defaultLocale.toString())
//                .collect(Collectors.toList());
//    }

//    @Override
//    public List<CategoryTranslate> findAllByLocale() {
//        return null;
//    }
}

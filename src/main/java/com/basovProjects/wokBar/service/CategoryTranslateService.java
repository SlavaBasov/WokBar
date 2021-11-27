package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Language;
import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.model.category.CategoryTranslate;

import java.util.List;

public interface CategoryTranslateService<I, E> {
    boolean save(Category category, Language language);
    boolean saveAllLocales(Category category);
    boolean update(E e) throws MyObjectNotFoundException;
    E findByCategoryIdAndLanguageId(I categoryId, String languageId) throws MyObjectNotFoundException;
    List<E> findAllByLanguageId(String languageId);
    boolean delete(I id);
    boolean deleteAllByCategoryId(I categoryId);
}

package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.model.category.CategoryTranslate;

import java.util.List;

public interface CategoryTranslateService<I, E> {
//    boolean save(E e);
    boolean update(E e);
//    boolean delete(I id);
//    List<E> findAllByCategoryId(I id);
//    List<E> findAllByCategoryIdWithoutEnLocal(I id);
    E findTranslationToRussianByCategoryId(I categoryId);
//    List<E> findAllByLocale();
}

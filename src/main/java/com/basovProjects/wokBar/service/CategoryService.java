package com.basovProjects.wokBar.service;

import java.util.List;

public interface CategoryService<I, E>{
    boolean save(E e);
    boolean update(E e);
    boolean delete(I id);
    E findById(I id);
    E findByIdWithLocalization(I id);
    List<E> findAllCategories();
//    List<CategoryTranslate> findCategories();
}

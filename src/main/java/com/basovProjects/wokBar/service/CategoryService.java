package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;

import java.util.List;

public interface CategoryService<I, E>{
    boolean save(E e);
    boolean update(E e) throws MyObjectNotFoundException;
    boolean delete(I id);
    E findById(I id) throws MyObjectNotFoundException;
    E findByIdWithLocalization(I id) throws MyObjectNotFoundException;
    List<E> findAllCategories();
    List<E> findAllCategoriesWithLocalization();
}

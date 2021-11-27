package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.exceptions.MyObjectIsAlreadyExistException;
import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Product;

import java.util.List;

public interface ProductService<I, E> {
    boolean saveNewProduct(E e) throws MyObjectIsAlreadyExistException, MyObjectNotFoundException;
    boolean updateProduct(E e) throws MyObjectNotFoundException;
    E getProductById(I id) throws MyObjectNotFoundException;
    List<E> getAllProducts();
    List<Product> getAllProductsByCategoryId(I categoryId);
    boolean deleteProduct(I id);
}

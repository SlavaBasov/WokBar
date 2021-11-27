package com.basovProjects.wokBar.service.impl;

import com.basovProjects.wokBar.exceptions.MyObjectIsAlreadyExistException;
import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Product;
import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.repository.ProductRepository;
import com.basovProjects.wokBar.service.CategoryService;
import com.basovProjects.wokBar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService<Long, Product> {

    private final ProductRepository productRepository;
    private final CategoryService<Long, Category> categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService<Long, Category> categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public boolean saveNewProduct(Product product) throws MyObjectNotFoundException, MyObjectIsAlreadyExistException {
        if(isNameOfProductExist(product.getName())){
            return false;
        }
        product.setCategory(categoryService.findById(product.getCategory().getId()));
        productRepository.save(product);
        return true;
    }

    private boolean isNameOfProductExist(String name){
        Product product = productRepository.findByName(name);
        return product != null;
    }

    @Override
    @Transactional
    public boolean updateProduct(Product product) throws MyObjectNotFoundException {
        Product foundProduct = getProductById(product.getId());
        if(!foundProduct.getName().equals(product.getName()) && isNameOfProductExist(product.getName())){
            return false;
        }
//        if(isNameOfProductExist(product.getName())){
//            if(!product.getName().equals(foundProduct.getName())) {
//                return false;
//            }
//        }
        foundProduct.setName(product.getName());
        foundProduct.setCategory(categoryService.findById(product.getCategory().getId()));
        foundProduct.setDescription(product.getDescription());
        foundProduct.setImageUrl(product.getImageUrl());
        foundProduct.setPrice((product.getPrice()));
        return true;
    }

    @Override
    public Product getProductById(Long id) throws MyObjectNotFoundException {
        Product product;
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            product = productOptional.get();
        } else {
            throw new MyObjectNotFoundException("Not found the product with id=" + id);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategoryId(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }


}

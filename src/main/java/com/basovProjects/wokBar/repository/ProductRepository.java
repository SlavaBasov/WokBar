package com.basovProjects.wokBar.repository;

import com.basovProjects.wokBar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findAllByCategory_Id(Long categoryId);
}

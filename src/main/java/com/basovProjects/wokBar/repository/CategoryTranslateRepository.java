package com.basovProjects.wokBar.repository;

import com.basovProjects.wokBar.model.category.CategoryTranslate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryTranslateRepository extends JpaRepository<CategoryTranslate, Long> {
    Optional<CategoryTranslate> findByLanguage_IdAndCategory_Id(String languageId, Long categoryId);
    List<CategoryTranslate> findAllByLanguage_Id(String id);
    List<CategoryTranslate> findAllByCategory_Id(Long id);
}

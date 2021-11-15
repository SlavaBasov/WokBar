package com.basovProjects.wokBar.repository;

import com.basovProjects.wokBar.model.CategoryTranslate;
import com.basovProjects.wokBar.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryTranslateRepository extends JpaRepository<CategoryTranslate, Long> {
    List<CategoryTranslate> findAllByLanguage(Language language);
}

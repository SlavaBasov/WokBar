package com.basovProjects.wokBar.repository;

import com.basovProjects.wokBar.model.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    public void deleteAllByOrder_Id(Long id);
}

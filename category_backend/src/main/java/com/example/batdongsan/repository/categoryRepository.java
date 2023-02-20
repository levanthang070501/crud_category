package com.example.batdongsan.repository;

import com.example.batdongsan.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface categoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByName(String name );

}

package com.example.batdongsan.service;

import com.example.batdongsan.ResourceNotFoundException;
import com.example.batdongsan.model.Category;
import com.example.batdongsan.repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    categoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        List<Category> cate = categoryRepository.findAll();
        return cate;
    }

    public Optional<Category> getCategoryById(Long id) {
        Optional<Category> cate = categoryRepository.findById(id);
        return cate;
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }
    public void delete(Long id)
    {
        categoryRepository.deleteById(id);
    }
    public List<Category>getcateByName(String name)
    {
        List<Category> cate=categoryRepository.findByName(name);
        return cate;
    }

    public Category addcategory(Category cate)
    {
        Category c=categoryRepository.save(cate);
        return c;
    }

    public void deleteacate(Long id)
    {
        categoryRepository.deleteById(id);
    }
    public Category findbyid(Long id){
      Category cate  =categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));
        return cate;
    }
}

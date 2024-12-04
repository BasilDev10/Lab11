package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;



    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Integer id){
        return categoryRepository.findCategoryById(id);
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public void updateCategory(Integer id , Category category){
        if(categoryRepository.findCategoryById(id) == null ) throw new ApiException("Error: category not found");

        category.setId(id);
        categoryRepository.save(category);
    }
    public void deleteCategory(Integer id ){
        if(categoryRepository.findCategoryById(id) == null ) throw new ApiException("Error: category not found");

        categoryRepository.deleteById(id);
    }
}

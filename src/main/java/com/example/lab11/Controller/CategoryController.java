package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.ApiResponse;
import com.example.lab11.Model.Category;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService  categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity getCategoryByName(@PathVariable String name){
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category , Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(new ApiResponse("category is added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category , Errors errors){
        if (errors.hasErrors())return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        categoryService.updateCategory(id,category);
        return ResponseEntity.status(200).body(new ApiResponse("category is updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){

        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("category is deleted"));
    }


}


package com.backendserver3.BackendServer3.Controller;

import com.backendserver3.BackendServer3.Model.Category;
import com.backendserver3.BackendServer3.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }


    @GetMapping("/walmart/{categoryname}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String categoryname) {
        Optional<Category> category = categoryService.getCategoryByName(categoryname);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/deals")
    public List<Category> getAllData(){
        return categoryService.getAllData();
    }
}

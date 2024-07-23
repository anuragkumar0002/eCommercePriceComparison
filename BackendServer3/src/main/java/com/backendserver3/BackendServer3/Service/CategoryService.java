package com.backendserver3.BackendServer3.Service;

import com.backendserver3.BackendServer3.Model.Category;
import com.backendserver3.BackendServer3.Model.DealItem;
import com.backendserver3.BackendServer3.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category saveCategory(Category category) {
        if (category.getDealItems() != null) {
            for (DealItem item : category.getDealItems()) {
                item.setCategory(category);
            }
        }
        return categoryRepository.save(category);
    }


    public Optional<Category> getCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public List<Category> getAllData(){
        List<Category> data = categoryRepository.findAll();
        System.out.println(data);
        return data;
    }

}

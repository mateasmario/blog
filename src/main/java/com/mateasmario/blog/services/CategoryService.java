package com.mateasmario.blog.services;

import com.mateasmario.blog.pojos.Category;
import com.mateasmario.blog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryByCategoryId(int categoryId) {
        return categoryRepository.findCategoryByCategoryId(categoryId).stream().findFirst().orElse(null);
    }
}

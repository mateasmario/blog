package com.mateasmario.blog.repositories;

import com.mateasmario.blog.pojos.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Optional<Category> findCategoryByCategoryId(int categoryId);
}

package com.mateasmario.blog.repositories;

import com.mateasmario.blog.pojos.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {
    Optional<Post> findPostByPostId(String postId);
}

package com.mateasmario.blog.services;

import com.mateasmario.blog.exceptions.CategoryNotFoundException;
import com.mateasmario.blog.exceptions.PostIdNotFoundException;
import com.mateasmario.blog.pojos.Post;
import com.mateasmario.blog.repositories.PostRepository;
import com.mateasmario.blog.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    private Post initializeNewPost(String title, String content, int[] categories) {
        Post post = new Post();

        String postId = null;

        do {
            postId = IdUtils.generatePostId();
        } while(!postRepository.findPostByPostId(postId).isEmpty());

        post.setPostId(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setCreationDate(LocalDateTime.now().toString());
        post.setUpdateDate(LocalDateTime.now().toString());

        checkIfCategoriesExist(categories);

        post.setCategories(categories);

        return post;
    }

    private void checkIfCategoriesExist(int[] categories) {
        for(int category : categories) {
            if (categoryService.getCategoryByCategoryId(category) == null) {
                throw new CategoryNotFoundException("Category " + category + " not found.");
            }
        }
    }

    public Post getPost(String postId) {
        Optional<Post> optional = postRepository.findPostByPostId(postId);

        if (optional.isEmpty()) {
            throw new PostIdNotFoundException("Post with ID " + postId + " does not exist.");
        }

        return optional.get();
    }

    public Post createPost(String title, String content, int[] categories) {
        Post post = initializeNewPost(title, content, categories);
        return postRepository.insert(post);
    }

    public Post updatePost(String postId, String title, String content, int[] categories) {
        Post post = null;

        Optional<Post> optional = postRepository.findPostByPostId(postId);

        if (optional.isEmpty()) {
            throw new PostIdNotFoundException("Post with ID " + postId + " doesn't exist.");
        }
        else {
            post = optional.get();

            post.setTitle(title);
            post.setContent(content);
            post.setCategories(categories);

            checkIfCategoriesExist(categories);

            return postRepository.save(post);
        }
    }

    public void delete(String postId) {
        Optional<Post> optional = postRepository.findPostByPostId(postId);

        if (optional.isEmpty()) {
            throw new PostIdNotFoundException("Post with ID " + postId + " doesn't exist.");
        }
        else {
            postRepository.delete(optional.get());
        }
    }
}

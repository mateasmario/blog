package com.mateasmario.blog.controllers;

import com.mateasmario.blog.exceptions.CategoryNotFoundException;
import com.mateasmario.blog.exceptions.PostIdNotFoundException;
import com.mateasmario.blog.pojos.Post;
import com.mateasmario.blog.services.PostService;
import com.mateasmario.blog.to.PostTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity getPosts() {
        List<Post> posts = postService.getPosts();
        ResponseEntity responseEntity = new ResponseEntity(posts, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity getPost(@PathVariable String id) {
        Post post = postService.getPost(id);

        if (post != null)
            return new ResponseEntity(post, HttpStatus.OK);
        else {
            return new ResponseEntity("Post with specified ID does not exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity create(@RequestBody PostTo postTo) {
        Post post = null;

        try {
            post = postService.createPost(postTo.getTitle(), postTo.getContent(), postTo.getCategories());
        } catch(CategoryNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(post, HttpStatus.CREATED);
    }

    @PutMapping("/posts")
    public ResponseEntity update(@RequestParam String postId, @RequestBody PostTo postTo) {
        Post post = null;

        try {
            post = postService.updatePost(postId, postTo.getTitle(), postTo.getContent(), postTo.getCategories());
        } catch(CategoryNotFoundException | PostIdNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/delete")
    public ResponseEntity delete(@RequestParam String postId) {
        try {
            postService.delete(postId);
        } catch(PostIdNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}

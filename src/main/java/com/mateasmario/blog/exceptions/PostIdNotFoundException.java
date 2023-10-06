package com.mateasmario.blog.exceptions;

public class PostIdNotFoundException extends RuntimeException {
    public PostIdNotFoundException(String message) {
        super(message);
    }
}

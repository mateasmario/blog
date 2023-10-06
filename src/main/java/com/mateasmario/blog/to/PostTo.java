package com.mateasmario.blog.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostTo {
    private String title;
    private String content;
    private int[] categories;
}

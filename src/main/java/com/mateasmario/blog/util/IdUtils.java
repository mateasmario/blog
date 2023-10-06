package com.mateasmario.blog.util;

import org.apache.commons.lang3.RandomStringUtils;

public class IdUtils {
    public static String generatePostId() {
        return RandomStringUtils.random(8, true, true);
    }
}

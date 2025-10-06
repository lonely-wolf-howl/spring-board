package com.jay.springboard.dto;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        String content,
        LocalDateTime createdAt,
        String createdBy
) {
    public static ArticleCommentDto of (String content, LocalDateTime createdAt, String createdBy) {
        return new ArticleCommentDto(content, createdAt, createdBy);
    }
}

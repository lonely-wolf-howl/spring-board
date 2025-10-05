package com.jay.springboard.service;

import com.jay.springboard.domain.constant.SearchType;
import com.jay.springboard.dto.ArticleDto;
import com.jay.springboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    @InjectMocks private ArticleService articleService;

    @Mock private ArticleRepository articleRepository;

    @DisplayName("[business logic] - search articles")
    @Test
    void searchArticles() {
        Page<ArticleDto> articles = articleService.searchArticles(SearchType.TITLE, "search keyword");

        assertThat(articles).isNotNull();
    }

    @DisplayName("[business logic] - search article by id")
    @Test
    void searchArticleById() {
        ArticleDto article = articleService.searchArticle(1L);

        assertThat(article).isNotNull();
    }
}

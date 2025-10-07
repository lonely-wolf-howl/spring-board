package com.jay.springboard.service;

import com.jay.springboard.domain.Article;
import com.jay.springboard.domain.UserAccount;
import com.jay.springboard.domain.constant.SearchType;
import com.jay.springboard.dto.ArticleDto;
import com.jay.springboard.dto.ArticleUpdateDto;
import com.jay.springboard.dto.UserAccountDto;
import com.jay.springboard.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
  @InjectMocks
  private ArticleService articleService;

  @Mock
  private ArticleRepository articleRepository;

  @DisplayName("[business logic] - search articles")
  @Test
  void searchArticles() {
    // when
    Page<ArticleDto> articles = articleService.searchArticles(SearchType.TITLE, "keyword");

    // then
    assertThat(articles).isNotNull();
  }

  @Disabled
  @DisplayName("[business logic] - search article by id")
  @Test
  void searchArticleById() {
    // when
    ArticleDto article = articleService.searchArticle(1L);

    // then
    assertThat(article).isNotNull();
  }

  @Disabled
  @DisplayName("[business logic] - create article")
  @Test
  void createArticle() {
    // given
    ArticleDto dto = articleDto("title", "content", "#java");
    given(articleRepository.save(any(Article.class))).willReturn(article());

    // when
    articleService.saveArticle(dto);

    // then
    then(articleRepository).should().save(any(Article.class));
  }

  @Disabled
  @DisplayName("[business logic] - update article")
  @Test
  void updateArticle() {
    // given
    given(articleRepository.save(any(Article.class))).willReturn(null);

    // when
    articleService.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));

    // then
    then(articleRepository).should().save(any(Article.class));
  }

  @Disabled
  @DisplayName("[business logic] - delete article")
  @Test
  void deleteArticle() {
    // given
    willDoNothing().given(articleRepository).delete(any(Article.class));

    // when
    articleService.deleteArticle(1L);

    // then
    then(articleRepository).should().delete(any(Article.class));
  }

  private Article article() {
    return Article.of(
        userAccount(),
        "title",
        "content",
        "#java"
    );
  }

  private UserAccount userAccount() {
    return UserAccount.of(
        "id",
        "password",
        "email@naver.com",
        "nickname",
        "memo"
    );
  }

  private ArticleDto articleDto(String title, String content, String hashtag) {
    return ArticleDto.of(
        1L,
        userAccountDto(),
        title,
        content,
        hashtag,
        LocalDateTime.now(),
        "user",
        LocalDateTime.now(),
        "user"
    );
  }

  private UserAccountDto userAccountDto() {
    return UserAccountDto.of(
        1L,
        "id",
        "password",
        "email@naver.com",
        "nickname",
        "memo",
        LocalDateTime.now(),
        "user",
        LocalDateTime.now(),
        "user"
    );
  }
}

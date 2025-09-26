package com.jay.springboard.repository;

import com.jay.springboard.config.JpaConfig;
import com.jay.springboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA connection test")
@Import({JpaConfig.class})
@DataJpaTest
public class JpaRepositoryTest {

  private final ArticleRepository articleRepository;
  private final ArticleCommentRepository articleCommentRepository;


  public JpaRepositoryTest(
          @Autowired ArticleRepository articleRepository,
          @Autowired ArticleCommentRepository articleCommentRepository) {
    this.articleRepository = articleRepository;
    this.articleCommentRepository = articleCommentRepository;
  }

  @DisplayName("select test")
  @Test
  void selectTest() {
    // when
    List<Article> articles = articleRepository.findAll();

    // then
    assertThat(articles).isNotNull().hasSize(1);
  }

  @DisplayName("insert test")
  @Test
  void insertTest() {
    // given
    long previousCount = articleRepository.count();
    Article article = Article.of("title", "content", "#spring");

    // when
    articleRepository.save(article);

    // then
    assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
  }
}

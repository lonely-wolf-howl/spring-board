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

@DisplayName("jpa connection test")
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
  void select() {
    // when
    List<Article> articles = articleRepository.findAll();

    // then
    assertThat(articles).isNotNull().hasSize(1);
  }

  @DisplayName("insert test")
  @Test
  void insert() {
    // given
    long previousCount = articleRepository.count();
    Article article = Article.of("title", "content", "#spring");

    // when
    articleRepository.save(article);

    // then
    assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
  }

  @DisplayName("update test")
  @Test
  void update() {
    // given
    Article article = articleRepository.findById(1L).orElseThrow();

    String updatedHashtag = "#spring-boot";
    article.setHashtag(updatedHashtag);

    // when
    Article updatedArticle = articleRepository.saveAndFlush(article);

    // then
    assertThat(updatedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
  }


  @DisplayName("delete test")
  @Test
  void delete() {
    // given
    Article article = articleRepository.findById(1L).orElseThrow();

    long previousArticleCount = articleRepository.count();
    long previousArticleCommentCount = articleCommentRepository.count();

    int deletedCommentSize = article.getArticleComments().size();

    // when
    articleRepository.delete(article);

    // then
    assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
    assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentSize);
  }
}

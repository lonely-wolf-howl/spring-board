package com.jay.springboard.service;

import com.jay.springboard.domain.Article;
import com.jay.springboard.domain.ArticleComment;
import com.jay.springboard.domain.UserAccount;
import com.jay.springboard.dto.ArticleCommentDto;
import com.jay.springboard.repository.ArticleCommentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class ArticleCommentServiceTest {
  @InjectMocks
  private ArticleCommentService articleCommentService;

  @Mock
  private ArticleCommentRepository articleCommentRepository;

  @Disabled
  @DisplayName("[business logic] - get comments by article id")
  @Test
  void getCommentsByArticleId() {
    // given
    Long articleId = 1L;
    ArticleComment expected = createArticleComment("content");
    given(articleCommentRepository.findByArticleId(articleId)).willReturn(List.of(expected));

    // when
    List<ArticleCommentDto> actual = articleCommentService.searchArticleComments(articleId);

    // then
    assertThat(actual).hasSize(1).first().hasFieldOrPropertyWithValue("content", expected.getContent());
    then(articleCommentRepository).should().findByArticleId(articleId);
  }

  private ArticleComment createArticleComment(String content) {
    return ArticleComment.of(
        Article.of(createUserAccount(), "title", "content", "#java"),
        createUserAccount(),
        content
    );
  }

  private UserAccount createUserAccount() {
    return UserAccount.of(
        "id",
        "password",
        "email@naver.com",
        "nickname",
        "memo"
    );
  }
}

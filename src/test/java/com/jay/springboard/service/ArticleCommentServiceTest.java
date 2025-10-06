package com.jay.springboard.service;

import com.jay.springboard.domain.Article;
import com.jay.springboard.dto.ArticleCommentDto;
import com.jay.springboard.repository.ArticleCommentRepository;
import com.jay.springboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleCommentServiceTest {
    @InjectMocks private ArticleCommentService articleCommentService;

    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;

    @DisplayName("[business logic] - get comments by article id")
    @Test
    void getCommentsByArticleId() {
        // given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title", "content", "#java"))
        );

        // when
        List<ArticleCommentDto> articleComments = articleCommentService.searchArticleComments(articleId);

        // then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }
}

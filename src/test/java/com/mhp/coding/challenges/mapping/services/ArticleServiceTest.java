package com.mhp.coding.challenges.mapping.services;

import com.mhp.coding.challenges.mapping.mappers.CustomArticleMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.repositories.ArticleRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ArticleServiceTest {

    private static final Long ARTICLE_ONE_ID = 1001L;

    @Mock
    private ArticleRepository articleRepoMock;

    @Mock
    private CustomArticleMapper mapper;

    @InjectMocks
    private ArticleService articleService;

    private Article article;
    private ArticleDto articleDto;

    @Before
    public void init(){
        initArticle(ARTICLE_ONE_ID);
        initArticleDto(ARTICLE_ONE_ID);
    }

    @Test
    public void findArticleById(){
        Mockito.when(articleRepoMock.findBy(ARTICLE_ONE_ID)).thenReturn(Optional.of(article));
        Mockito.when(mapper.toArticleDto(article)).thenReturn(articleDto);

        ArticleDto articleDto = articleService.articleForId(ARTICLE_ONE_ID);

        assertThat(articleDto).isNotNull();
        Mockito.verify(articleRepoMock, Mockito.times(1)).findBy(Mockito.anyLong());
        Mockito.verify(mapper, Mockito.times(1)).toArticleDto(article);
        Mockito.verifyNoMoreInteractions(articleRepoMock);
    }

    @Test
    public void findAllArticles(){
        Mockito.when(articleRepoMock.all()).thenReturn(Arrays.asList(article, article));
        Mockito.when(mapper.toArticleDto(article)).thenReturn(articleDto);

        List<ArticleDto> articleDto = articleService.list();

        assertThat(articleDto).isNotNull();
        Mockito.verify(articleRepoMock, Mockito.times(1)).all();
        Mockito.verify(mapper, Mockito.times(2)).toArticleDto(article);
        Mockito.verifyNoMoreInteractions(articleRepoMock);
    }

    @Test
    public void findArticleById_ThrowNotFound(){
        Mockito.when(articleRepoMock.findBy(100001L)).thenReturn(Optional.of(article));

        assertThrows(ArticleNotFoundException.class, () -> { articleService.articleForId(ARTICLE_ONE_ID); });
        Mockito.verify(articleRepoMock, Mockito.times(1)).findBy(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(articleRepoMock);
        Mockito.verifyZeroInteractions(mapper);
    }

    private void initArticle(Long id) {
        article = new Article();
        article.setId(id);
        article.setAuthor("Max Mustermann");
        article.setDescription("Article Description "+id);
        article.setTitle("Article Nr.: "+id);
        article.setBlocks(new HashSet());
    }

    private void initArticleDto(Long id) {
        articleDto = new ArticleDto();
        articleDto.setId(id);
        articleDto.setAuthor("Max Mustermann");
        articleDto.setDescription("Article Description "+id);
        articleDto.setTitle("Article Nr.: "+id);
        articleDto.setBlocks(new ArrayList());
    }

}

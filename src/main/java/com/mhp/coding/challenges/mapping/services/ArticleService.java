package com.mhp.coding.challenges.mapping.services;

import com.mhp.coding.challenges.mapping.mappers.CustomArticleMapper;
import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private CustomArticleMapper customArticleMapper;

    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository, CustomArticleMapper customArticleMapper) {
        this.repository = repository;
        this.customArticleMapper = customArticleMapper;
    }

    public List<ArticleDto> list() {
        final List<Article> articles = repository.all();

        return articles.stream()
                .map(customArticleMapper::toArticleDto)
                .collect(Collectors.toList());
    }

    public ArticleDto articleForId(Long id) {
        final Article article = repository.findBy(id)
                .orElseThrow(() -> new ArticleNotFoundException("Could not find any article with id: " + id));

        return customArticleMapper.toArticleDto(article);
    }

    public ArticleDto create(ArticleDto articleDto) {
        final Article create = customArticleMapper.toArticle(articleDto);
        repository.create(create);
        return customArticleMapper.toArticleDto(create);
    }
}

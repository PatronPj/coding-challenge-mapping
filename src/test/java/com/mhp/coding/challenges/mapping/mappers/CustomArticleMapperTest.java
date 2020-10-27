package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.ImageSize;
import com.mhp.coding.challenges.mapping.models.db.blocks.*;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.ImageDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArticleMapperTest {

    private CustomArticleMapper mapper = Mappers.getMapper(CustomArticleMapper.class);

    @Test
    public void givenArticleMappingToArticleDto_whenMaps_thenCorrect() {
        Article article = new Article();
        article.setId(1001L);
        article.setAuthor("Max Mustermann");
        article.setDescription("Article Description 1001");
        article.setTitle("Article Nr.:1001");

        ArticleDto articleDto = mapper.toArticleDto(article);

        assertEquals(article.getId(), articleDto.getId());
        assertEquals(article.getDescription(), articleDto.getDescription());
        assertEquals(article.getTitle(), articleDto.getTitle());
        assertEquals(article.getAuthor(), articleDto.getAuthor());
    }

    @Test
    public void givenArticleDtoMappingToArticle_whenMaps_thenCorrect() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(1001L);
        articleDto.setAuthor("Max Mustermann");
        articleDto.setDescription("Article Description 1001");
        articleDto.setTitle("Article Nr.:1001");

        Article article = mapper.toArticle(articleDto);

        assertEquals(articleDto.getId(), article.getId());
        assertEquals(articleDto.getDescription(), article.getDescription());
        assertEquals(articleDto.getTitle(), article.getTitle());
        assertEquals(articleDto.getAuthor(), article.getAuthor());
    }

    @Test
    public void givenTextBlockMappingToTextBlockDto_whenMaps_thenCorrect() {
        TextBlock textBlock = new TextBlock();
        textBlock.setSortIndex(0);
        textBlock.setText("Some Text for 1001");

        com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock textBlockDto = mapper.toTextBlockDto(textBlock);

        assertEquals(textBlock.getSortIndex(), textBlockDto.getSortIndex());
        assertEquals(textBlock.getText(), textBlockDto.getText());
    }

    @Test
    public void givenTextBlockDtoMappingToTextBlock_whenMaps_thenCorrect() {
        com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock textBlockDto = new com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock();
        textBlockDto.setSortIndex(0);
        textBlockDto.setText("Some Text for 1001");

        TextBlock textBlock = mapper.toTextBlock(textBlockDto);

        assertEquals(textBlockDto.getSortIndex(), textBlock.getSortIndex());
        assertEquals(textBlockDto.getText(), textBlock.getText());
    }

    @Test
    public void givenGalleryBlockMappingToGalleryBlockDto_whenMaps_thenCorrect() {
        List<Image> imageList = setImages();

        GalleryBlock galleryBlock = new GalleryBlock();
        galleryBlock.setSortIndex(0);
        galleryBlock.setImages(imageList);

        com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto galleryBlockDto = mapper.toGalleryBlockDto(galleryBlock);

        assertEquals(galleryBlock.getSortIndex(), galleryBlockDto.getSortIndex());
        assertEquals(galleryBlock.getImages().get(0).getId(), galleryBlockDto.getImages().get(0).getId());
        assertEquals(galleryBlock.getImages().get(0).getUrl(), galleryBlockDto.getImages().get(0).getUrl());
        assertEquals(galleryBlock.getImages().get(0).getImageSize(), galleryBlockDto.getImages().get(0).getImageSize());

        assertEquals(galleryBlock.getSortIndex(), galleryBlockDto.getSortIndex());
        assertEquals(galleryBlock.getImages().get(1).getId(), galleryBlockDto.getImages().get(1).getId());
        assertEquals(galleryBlock.getImages().get(1).getUrl(), galleryBlockDto.getImages().get(1).getUrl());
        assertEquals(galleryBlock.getImages().get(1).getImageSize(), galleryBlockDto.getImages().get(1).getImageSize());
    }

    @Test
    public void givenImageBlockMappingToImageBlockDto_whenMaps_thenCorrect() {
        final Image image = new Image();
        image.setId(1001L);
        image.setImageSize(ImageSize.LARGE);
        image.setUrl("https://someurl.com/image/1");

        ImageBlock imageBlock = new ImageBlock();
        imageBlock.setSortIndex(0);
        imageBlock.setImage(image);

        com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock imageBlockDto = mapper.toImageBlockDto(imageBlock);

        assertEquals(imageBlock.getSortIndex(), imageBlockDto.getSortIndex());
        assertEquals(imageBlock.getImage().getImageSize(), imageBlockDto.getImage().getImageSize());
        assertEquals(imageBlock.getImage().getUrl(), imageBlockDto.getImage().getUrl());
    }

    @Test
    public void givenImageBlockDtoMappingToImageBlock_whenMaps_thenCorrect() {
        final ImageDto image = new ImageDto();
        image.setId(1001L);
        image.setImageSize(ImageSize.LARGE);
        image.setUrl("https://someurl.com/image/1");

        com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock imageBlockDto = new com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock();
        imageBlockDto.setSortIndex(0);
        imageBlockDto.setImage(image);

        ImageBlock imageBlock = mapper.toImageBlock(imageBlockDto);

        assertEquals(imageBlockDto.getSortIndex(), imageBlock.getSortIndex());
        assertEquals(imageBlockDto.getImage().getImageSize(), imageBlock.getImage().getImageSize());
        assertEquals(imageBlockDto.getImage().getUrl(), imageBlock.getImage().getUrl());
    }

    @Test
    public void givenVideoBlockMappingToVideoBlockDto_whenMaps_thenCorrect() {
        VideoBlock videoBlock = new VideoBlock();
        videoBlock.setSortIndex(0);
        videoBlock.setType(VideoBlockType.YOUTUBE);
        videoBlock.setUrl("https://youtu.be/myvideo");

        com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock videoBlockDto = mapper.toVideoBlockDto(videoBlock);

        assertEquals(videoBlock.getSortIndex(), videoBlockDto.getSortIndex());
        assertEquals(videoBlock.getType(), videoBlockDto.getType());
        assertEquals(videoBlock.getUrl(), videoBlockDto.getUrl());
    }

    @Test
    public void givenVideoBlockDtoMappingToVideoBlock_whenMaps_thenCorrect() {
        com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock videoBlockDto = new com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock();
        videoBlockDto.setSortIndex(0);
        videoBlockDto.setType(VideoBlockType.YOUTUBE);
        videoBlockDto.setUrl("https://youtu.be/myvideo");

        VideoBlock videoBlock = mapper.toVideoBlock(videoBlockDto);

        assertEquals(videoBlockDto.getSortIndex(), videoBlock.getSortIndex());
        assertEquals(videoBlockDto.getType(), videoBlock.getType());
        assertEquals(videoBlockDto.getUrl(), videoBlock.getUrl());
    }

    private List<Image> setImages() {
        final Image image1 = new Image();
        final Image image2 = new Image();
        final List<Image> imageList = new ArrayList<>();

        image1.setId(1001L);
        image1.setImageSize(ImageSize.LARGE);
        image1.setUrl("https://someurl.com/image/1");
        image2.setId(1002L);
        image2.setImageSize(ImageSize.MEDIUM);
        image2.setUrl("https://someurl.com/image/2");

        imageList.add(image1);
        imageList.add(image2);

        return imageList;
    }

}
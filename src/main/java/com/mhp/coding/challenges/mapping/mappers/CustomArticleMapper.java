package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.blocks.*;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomArticleMapper {

    Article toArticle(ArticleDto articleDto);
    ArticleDto toArticleDto(Article article);

    default ArticleBlockDto toArticleBlockDto(ArticleBlock articleBlock) {
        if(articleBlock instanceof TextBlock) {
            return toTextBlockDto((TextBlock) articleBlock);
        } else if(articleBlock instanceof GalleryBlock){
            return toGalleryBlockDto((GalleryBlock) articleBlock);
        } else if(articleBlock instanceof VideoBlock){
            return toVideoBlockDto((VideoBlock) articleBlock);
        } else if(articleBlock instanceof ImageBlock){
            return toImageBlockDto((ImageBlock) articleBlock);
        }
        return null;
    }

    default ArticleBlock toArticleBlock(ArticleBlockDto articleBlockDto) {
        if(articleBlockDto instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock) {
            return toTextBlock((com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock) articleBlockDto);
        } else if(articleBlockDto instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto){
            toGalleryBlock((com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto) articleBlockDto);
        } else if(articleBlockDto instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock){
            toVideoBlock((com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock) articleBlockDto);
        } else if (articleBlockDto instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock){
            toImageBlock((com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock) articleBlockDto);
        }
        return null;
    }

    default Collection<ArticleBlockDto> articleBlockSetToArticleBlockDtoCollection(Set<ArticleBlock> set){
        if ( set == null ) {
            return null;
        }

        ArrayList<ArticleBlockDto> collection = new ArrayList<>( set.size() );
        for ( ArticleBlock articleBlock : set ) {
            collection.add( toArticleBlockDto( articleBlock ) );
        }
        Collections.sort(collection);
        return collection;
    }

    com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock toTextBlockDto(TextBlock articleBlock);
    TextBlock toTextBlock(com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock articleBlock);

    com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto toGalleryBlockDto(GalleryBlock articleBlock);
    GalleryBlock toGalleryBlock(com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto articleBlock);

    com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock toVideoBlockDto(VideoBlock articleBlock);
    VideoBlock toVideoBlock(com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock articleBlock);

    com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock toImageBlockDto(ImageBlock articleBlock);
    ImageBlock toImageBlock(com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock articleBlock);

}

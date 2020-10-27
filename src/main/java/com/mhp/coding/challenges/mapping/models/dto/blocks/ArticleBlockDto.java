package com.mhp.coding.challenges.mapping.models.dto.blocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleBlockDto implements Comparable<ArticleBlockDto>{

    private int sortIndex;

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Override
    public int compareTo(ArticleBlockDto otherArticleBlockDto) {
        return Integer.compare(getSortIndex(), otherArticleBlockDto.getSortIndex());
    }
}

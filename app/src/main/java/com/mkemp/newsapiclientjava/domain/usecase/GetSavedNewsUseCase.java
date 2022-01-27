package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

import java.util.List;

public class GetSavedNewsUseCase
{
    private final NewsRepository newsRepository;

    public GetSavedNewsUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }

    public List<Article> execute()
    {
        return newsRepository.getSavedNews();
    }
}

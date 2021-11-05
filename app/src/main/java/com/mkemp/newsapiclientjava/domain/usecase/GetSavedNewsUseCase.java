package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;
import com.mkemp.newsapiclientjava.domain.repository.NewsRepository.SavedNewsHolder;

public class GetSavedNewsUseCase
{
    private final NewsRepository newsRepository;

    public GetSavedNewsUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }

    public void execute(final Article article, final SavedNewsHolder savedNewsHolder)
    {
        newsRepository.getSavedNews(article, savedNewsHolder);
    }
}

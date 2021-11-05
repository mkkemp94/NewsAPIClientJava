package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

public class DeleteSavedNewsUseCase
{
    private final NewsRepository newsRepository;

    public DeleteSavedNewsUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }

    public void execute(final Article article)
    {
        newsRepository.deleteNews(article);
    }
}

package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

public class DeleteSavedNewsUseCase
{
    private final NewsRepository newsRepository;

    public DeleteSavedNewsUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }
}

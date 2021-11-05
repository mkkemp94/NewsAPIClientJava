package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

public class GetSavedNewsUseCase
{
    private final NewsRepository newsRepository;

    public GetSavedNewsUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }
}

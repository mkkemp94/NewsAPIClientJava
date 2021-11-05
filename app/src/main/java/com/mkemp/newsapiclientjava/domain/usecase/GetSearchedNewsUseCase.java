package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

public class GetSearchedNewsUseCase
{
    private final NewsRepository newsRepository;

    public GetSearchedNewsUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }
}

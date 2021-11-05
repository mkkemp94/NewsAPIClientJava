package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

public class SaveNewsUseCase
{
    private final NewsRepository newsRepository;

    public SaveNewsUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }
}

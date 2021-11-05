package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

public class GetNewsHeadlines
{
    private final NewsRepository newsRepository;

    public GetNewsHeadlines(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }
}

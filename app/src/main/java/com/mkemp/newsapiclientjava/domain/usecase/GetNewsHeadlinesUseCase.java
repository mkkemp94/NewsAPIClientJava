package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.util.Resource;
import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

public class GetNewsHeadlinesUseCase
{
    private final NewsRepository newsRepository;

    public GetNewsHeadlinesUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }

    public Resource<APIResponse> execute()
    {
        // Can modify this or perform other logic here
        return newsRepository.getNewsHeadlines();
    }
}

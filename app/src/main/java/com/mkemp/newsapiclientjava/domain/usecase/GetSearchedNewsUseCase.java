package com.mkemp.newsapiclientjava.domain.usecase;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.util.Resource;
import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

public class GetSearchedNewsUseCase
{
    private final NewsRepository newsRepository;

    public GetSearchedNewsUseCase(final NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }

    public Resource<APIResponse> execute(final String country, final String searchQuery, final int page)
    {
        // Can modify this or perform other logic here
        return newsRepository.getSearchedNews(country, searchQuery, page);
    }
}

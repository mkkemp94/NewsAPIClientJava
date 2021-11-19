package com.mkemp.newsapiclientjava.data.repository.dataSourceImpl;

import com.mkemp.newsapiclientjava.BuildConfig;
import com.mkemp.newsapiclientjava.data.api.NewsAPIService;
import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.repository.dataSource.NewsRemoteDataSource;

import retrofit2.Response;

public class NewsRemoteDataSourceImpl implements NewsRemoteDataSource
{
    private final NewsAPIService newsAPIService;

    public NewsRemoteDataSourceImpl(final NewsAPIService newsAPIService)
    {
        this.newsAPIService = newsAPIService;
    }

    @Override
    public Response<APIResponse> getTopHeadlines(final String country,
                                                 final int page)
    {
        return newsAPIService.getTopHeadlines(country, page, BuildConfig.API_KEY);
    }
}

package com.mkemp.newsapiclientjava.data.repository.dataSourceImpl;

import com.mkemp.newsapiclientjava.BuildConfig;
import com.mkemp.newsapiclientjava.data.api.NewsAPIService;
import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.repository.dataSource.NewsRemoteDataSource;

import retrofit2.Response;

public class NewsRemoteDataSourceImpl implements NewsRemoteDataSource
{
    private final NewsAPIService newsAPIService;
    private final String country;
    private final String page;

    public NewsRemoteDataSourceImpl(final NewsAPIService newsAPIService,
                                    final String country,
                                    final String page)
    {
        this.newsAPIService = newsAPIService;
        this.country = country;
        this.page = page;
    }

    @Override
    public Response<APIResponse> getTopHeadlines()
    {
        return newsAPIService.getTopHeadlines(country, page, BuildConfig.API_KEY);
    }
}

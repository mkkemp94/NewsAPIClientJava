package com.mkemp.newsapiclientjava.data.repository.dataSource;

import com.mkemp.newsapiclientjava.data.model.APIResponse;

import retrofit2.Response;

public interface NewsRemoteDataSource
{
    Response<APIResponse> getTopHeadlines(final String country, final int page);

    Response<APIResponse> getSearchedNews(final String country, final String searchQuery, final int page);
}

package com.mkemp.newsapiclientjava.data.repository.dataSource;

import com.mkemp.newsapiclientjava.data.model.APIResponse;

import retrofit2.Response;

public interface NewsRemoteDataSource
{
    Response<APIResponse> getTopHeadlines();
}

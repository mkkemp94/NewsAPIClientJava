package com.mkemp.newsapiclientjava.data.api;

import com.mkemp.newsapiclientjava.data.model.APIResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIService
{
    @GET("v2/top-headlines")
    Response<APIResponse> getTopHeadlines(@Query("country") final String country,
                                          @Query("page") final int page,
                                          @Query("apiKey") final String apiKey);
}

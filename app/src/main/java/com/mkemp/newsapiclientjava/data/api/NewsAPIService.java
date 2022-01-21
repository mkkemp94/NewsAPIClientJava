package com.mkemp.newsapiclientjava.data.api;

import com.mkemp.newsapiclientjava.data.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIService
{
    @GET("v2/top-headlines")
    Call<APIResponse> getTopHeadlines(@Query("country") final String country,
                                      @Query("page") final int page,
                                      @Query("apiKey") final String apiKey);
}

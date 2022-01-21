package com.mkemp.newsapiclientjava.data.repository;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.data.repository.dataSource.NewsRemoteDataSource;
import com.mkemp.newsapiclientjava.data.util.Resource;
import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

import retrofit2.Response;

public class NewsRepositoryImpl implements NewsRepository
{
    private final NewsRemoteDataSource remoteDataSource;

    public NewsRepositoryImpl(final NewsRemoteDataSource remoteDataSource)
    {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Resource<APIResponse> getNewsHeadlines(final String country,
                                                  final int page)
    {
        return responseToResource(remoteDataSource.getTopHeadlines(country, page));
    }

    @Override
    public Resource<APIResponse> getSearchedNews(final String searchQuery)
    {
        return null;
    }

    @Override
    public void saveNews(final Article article)
    {

    }

    @Override
    public void deleteNews(final Article article)
    {

    }

    @Override
    public void getSavedNews(final Article article, final SavedNewsHolder savedNewsHolder)
    {

    }

    private Resource<APIResponse> responseToResource(final Response<APIResponse> response)
    {
        if (response == null) { return Resource.error("Null response", null); }

        if (response.isSuccessful())
        {
            final APIResponse body = response.body();
            if (body != null)
            {
                return Resource.success(body);
            }
        }

        return Resource.error(response.message(), null);
    }
}

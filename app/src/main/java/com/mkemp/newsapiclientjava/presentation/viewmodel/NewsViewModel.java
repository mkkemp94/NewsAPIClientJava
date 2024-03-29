package com.mkemp.newsapiclientjava.presentation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.data.util.Resource;
import com.mkemp.newsapiclientjava.domain.usecase.DeleteSavedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSavedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSearchedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.SaveNewsUseCase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class NewsViewModel extends AndroidViewModel
{
    private final Application application;
    private final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase;
    private final GetSearchedNewsUseCase getSearchedNewsUseCase;
    private final SaveNewsUseCase saveNewsUseCase;
    private final GetSavedNewsUseCase getSavedNewsUseCase;
    private final DeleteSavedNewsUseCase deleteSavedNewsUseCase;

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public NewsViewModel(final Application application,
                         final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase,
                         final GetSearchedNewsUseCase getSearchedNewsUseCase,
                         final SaveNewsUseCase saveNewsUseCase,
                         final GetSavedNewsUseCase getSavedNewsUseCase,
                         final DeleteSavedNewsUseCase deleteSavedNewsUseCase)
    {
        super(application);
        this.application = application;
        this.getNewsHeadlinesUseCase = getNewsHeadlinesUseCase;
        this.getSearchedNewsUseCase = getSearchedNewsUseCase;
        this.saveNewsUseCase = saveNewsUseCase;
        this.getSavedNewsUseCase = getSavedNewsUseCase;
        this.deleteSavedNewsUseCase = deleteSavedNewsUseCase;
    }

    // headlines

    public final MutableLiveData<Resource<APIResponse>> newsHeadLines = new MutableLiveData<>();

    public void getNewsHeadLines(final String country,
                                 final int page)
    {
        executor.execute(() ->
        {
            newsHeadLines.postValue(Resource.loading(null));
            try
            {
                if (isNetworkAvailable(application))
                {
                    final Resource<APIResponse> apiResult = getNewsHeadlinesUseCase.execute(country, page);
                    newsHeadLines.postValue(apiResult);
                }
                else
                {
                    newsHeadLines.postValue(Resource.error("Internet is not available", null));
                }
            }
            catch (Exception e)
            {
                newsHeadLines.postValue(Resource.error(e.getMessage(), null));
            }
        });
    }

    //search

    public final MutableLiveData<Resource<APIResponse>> searchedNews = new MutableLiveData<>();

    public void searchNews(final String country,
                           final String searchQuery,
                           final int page)
    {
        executor.execute(() ->
        {
            searchedNews.postValue(Resource.loading(null));
            try
            {
                if (isNetworkAvailable(application))
                {
                    final Resource<APIResponse> apiResult = getSearchedNewsUseCase.execute(country, searchQuery, page);
                    searchedNews.postValue(apiResult);
                }
                else
                {
                    searchedNews.postValue(Resource.error("Internet is not available", null));
                }
            }
            catch (Exception e)
            {
                searchedNews.postValue(Resource.error(e.getMessage(), null));
            }
        });
    }

    // local data

    public void saveArticle(Article article)
    {
        executor.execute(() ->
        {
            saveNewsUseCase.execute(article);
        });
    }

    // get saved

    public final MutableLiveData<List<Article>> savedNews = new MutableLiveData<>();

    public void getSavedNews()
    {
        executor.execute(() ->
        {
            final List<Article> articles = getSavedNewsUseCase.execute();
            savedNews.postValue(articles);
        });
    }

    // delete

    public void deleteArticle(final Article article)
    {
        executor.execute(() ->
        {
            deleteSavedNewsUseCase.execute(article);
        });
    }

    // util

    private boolean isNetworkAvailable(final Context context)
    {
        final ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null)
        {
            return false;
        }
        else
        {
            final NetworkInfo[] info = connectivity.getAllNetworkInfo();
            for (NetworkInfo networkInfo : info)
            {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }
}

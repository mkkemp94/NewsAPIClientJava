package com.mkemp.newsapiclientjava.presentation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.util.Resource;
import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSearchedNewsUseCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class NewsViewModel extends AndroidViewModel
{
    private final Application application;
    private final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase;
    private final GetSearchedNewsUseCase getSearchedNewsUseCase;

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public NewsViewModel(final Application application,
                         final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase,
                         final GetSearchedNewsUseCase getSearchedNewsUseCase)
    {
        super(application);
        this.application = application;
        this.getNewsHeadlinesUseCase = getNewsHeadlinesUseCase;
        this.getSearchedNewsUseCase = getSearchedNewsUseCase;
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

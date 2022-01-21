package com.mkemp.newsapiclientjava.presentation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.util.Resource;
import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class NewsViewModel extends AndroidViewModel
{
    private final Application application;
    private final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase;

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public final MutableLiveData<Resource<APIResponse>> newsHeadLines = new MutableLiveData<>();

    public NewsViewModel(final Application application,
                         final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase)
    {
        super(application);
        this.application = application;
        this.getNewsHeadlinesUseCase = getNewsHeadlinesUseCase;
    }

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

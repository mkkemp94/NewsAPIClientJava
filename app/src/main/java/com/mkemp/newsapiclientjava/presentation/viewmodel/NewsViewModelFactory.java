package com.mkemp.newsapiclientjava.presentation.viewmodel;

import android.app.Application;

import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSearchedNewsUseCase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewsViewModelFactory implements ViewModelProvider.Factory
{
    private final Application application;
    private final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase;
    private final GetSearchedNewsUseCase getSearchedNewsUseCase;

    public NewsViewModelFactory(final Application application,
                                final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase,
                                final GetSearchedNewsUseCase getSearchedNewsUseCase)
    {

        this.application = application;
        this.getNewsHeadlinesUseCase = getNewsHeadlinesUseCase;
        this.getSearchedNewsUseCase = getSearchedNewsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass)
    {
        return (T) new NewsViewModel(application, getNewsHeadlinesUseCase, getSearchedNewsUseCase);
    }
}

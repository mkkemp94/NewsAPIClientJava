package com.mkemp.newsapiclientjava.presentation.viewmodel;

import android.app.Application;

import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewsViewModelFactory implements ViewModelProvider.Factory
{
    private final Application application;
    private final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase;

    public NewsViewModelFactory(final Application application,
                                final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase)
    {

        this.application = application;
        this.getNewsHeadlinesUseCase = getNewsHeadlinesUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass)
    {
        return (T) new NewsViewModel(application, getNewsHeadlinesUseCase);
    }
}

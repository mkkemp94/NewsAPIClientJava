package com.mkemp.newsapiclientjava.presentation.viewmodel;

import android.app.Application;

import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSearchedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.SaveNewsUseCase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewsViewModelFactory implements ViewModelProvider.Factory
{
    private final Application application;
    private final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase;
    private final GetSearchedNewsUseCase getSearchedNewsUseCase;
    private final SaveNewsUseCase saveNewsUseCase;

    public NewsViewModelFactory(final Application application,
                                final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase,
                                final GetSearchedNewsUseCase getSearchedNewsUseCase,
                                final SaveNewsUseCase saveNewsUseCase)
    {

        this.application = application;
        this.getNewsHeadlinesUseCase = getNewsHeadlinesUseCase;
        this.getSearchedNewsUseCase = getSearchedNewsUseCase;
        this.saveNewsUseCase = saveNewsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass)
    {
        return (T) new NewsViewModel(application, getNewsHeadlinesUseCase, getSearchedNewsUseCase, saveNewsUseCase);
    }
}

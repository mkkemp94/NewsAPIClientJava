package com.mkemp.newsapiclientjava.presentation.viewmodel;

import android.app.Application;

import com.mkemp.newsapiclientjava.domain.usecase.DeleteSavedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSavedNewsUseCase;
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
    private final GetSavedNewsUseCase getSavedNewsUseCase;
    private final DeleteSavedNewsUseCase deleteSavedNewsUseCase;

    public NewsViewModelFactory(final Application application,
                                final GetNewsHeadlinesUseCase getNewsHeadlinesUseCase,
                                final GetSearchedNewsUseCase getSearchedNewsUseCase,
                                final SaveNewsUseCase saveNewsUseCase,
                                final GetSavedNewsUseCase getSavedNewsUseCase,
                                final DeleteSavedNewsUseCase deleteSavedNewsUseCase)
    {

        this.application = application;
        this.getNewsHeadlinesUseCase = getNewsHeadlinesUseCase;
        this.getSearchedNewsUseCase = getSearchedNewsUseCase;
        this.saveNewsUseCase = saveNewsUseCase;
        this.getSavedNewsUseCase = getSavedNewsUseCase;
        this.deleteSavedNewsUseCase = deleteSavedNewsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass)
    {
        return (T) new NewsViewModel(application, getNewsHeadlinesUseCase, getSearchedNewsUseCase, saveNewsUseCase, getSavedNewsUseCase, deleteSavedNewsUseCase);
    }
}

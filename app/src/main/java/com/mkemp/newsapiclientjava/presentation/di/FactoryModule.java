package com.mkemp.newsapiclientjava.presentation.di;

import android.app.Application;

import com.mkemp.newsapiclientjava.domain.usecase.DeleteSavedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSavedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSearchedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.SaveNewsUseCase;
import com.mkemp.newsapiclientjava.presentation.viewmodel.NewsViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FactoryModule
{
    @Singleton
    @Provides
    public NewsViewModelFactory provideNewsViewModelFactory(Application application,
                                                            GetNewsHeadlinesUseCase getNewsHeadlinesUseCase,
                                                            GetSearchedNewsUseCase getSearchedNewsUseCase,
                                                            SaveNewsUseCase saveNewsUseCase,
                                                            GetSavedNewsUseCase getSavedNewsUseCase,
                                                            DeleteSavedNewsUseCase deleteSavedNewsUseCase)
    {
        return new NewsViewModelFactory(application, getNewsHeadlinesUseCase, getSearchedNewsUseCase, saveNewsUseCase, getSavedNewsUseCase, deleteSavedNewsUseCase);
    }
}

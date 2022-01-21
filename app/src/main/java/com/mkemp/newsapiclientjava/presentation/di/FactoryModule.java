package com.mkemp.newsapiclientjava.presentation.di;

import android.app.Application;

import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;
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
                                                            GetNewsHeadlinesUseCase getNewsHeadlinesUseCase)
    {
        return new NewsViewModelFactory(application, getNewsHeadlinesUseCase);
    }
}
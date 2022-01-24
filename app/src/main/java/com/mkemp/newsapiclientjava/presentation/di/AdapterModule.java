package com.mkemp.newsapiclientjava.presentation.di;


import com.mkemp.newsapiclientjava.presentation.adapter.NewsAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AdapterModule
{
    @Singleton
    @Provides
    public NewsAdapter provideNewsAdapter()
    {
        return new NewsAdapter();
    }
}

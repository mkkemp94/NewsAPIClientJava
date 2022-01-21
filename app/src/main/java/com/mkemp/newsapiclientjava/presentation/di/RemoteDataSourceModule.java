package com.mkemp.newsapiclientjava.presentation.di;

import com.mkemp.newsapiclientjava.data.api.NewsAPIService;
import com.mkemp.newsapiclientjava.data.repository.dataSource.NewsRemoteDataSource;
import com.mkemp.newsapiclientjava.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RemoteDataSourceModule
{
    @Singleton
    @Provides
    public NewsRemoteDataSource provideNewsRemoteDataSource(NewsAPIService newsAPIService)
    {
        return new NewsRemoteDataSourceImpl(newsAPIService);
    }
}

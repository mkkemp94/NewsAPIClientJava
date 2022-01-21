package com.mkemp.newsapiclientjava.presentation.di;

import com.mkemp.newsapiclientjava.data.repository.NewsRepositoryImpl;
import com.mkemp.newsapiclientjava.data.repository.dataSource.NewsRemoteDataSource;
import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule
{
    @Singleton
    @Provides
    public NewsRepository provideNewsRepository(NewsRemoteDataSource remoteDataSource)
    {
        return new NewsRepositoryImpl(remoteDataSource);
    }
}

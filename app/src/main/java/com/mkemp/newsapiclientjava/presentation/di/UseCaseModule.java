package com.mkemp.newsapiclientjava.presentation.di;

import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;
import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule
{
    @Singleton
    @Provides
    public GetNewsHeadlinesUseCase provideNewsHeadlinesUseCase(NewsRepository newsRepository)
    {
        return new GetNewsHeadlinesUseCase(newsRepository);
    }
}

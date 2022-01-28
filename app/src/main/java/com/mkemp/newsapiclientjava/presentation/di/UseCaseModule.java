package com.mkemp.newsapiclientjava.presentation.di;

import com.mkemp.newsapiclientjava.domain.repository.NewsRepository;
import com.mkemp.newsapiclientjava.domain.usecase.DeleteSavedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetNewsHeadlinesUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSavedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.GetSearchedNewsUseCase;
import com.mkemp.newsapiclientjava.domain.usecase.SaveNewsUseCase;

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

    @Singleton
    @Provides
    public GetSearchedNewsUseCase provideGetSearchedNewsUseCase(NewsRepository newsRepository)
    {
        return new GetSearchedNewsUseCase(newsRepository);
    }

    @Singleton
    @Provides
    public SaveNewsUseCase provideSaveNewsUseCase(NewsRepository newsRepository)
    {
        return new SaveNewsUseCase(newsRepository);
    }

    @Singleton
    @Provides
    public GetSavedNewsUseCase provideGetSavedNewsUseCase(NewsRepository newsRepository)
    {
        return new GetSavedNewsUseCase(newsRepository);
    }

    @Singleton
    @Provides
    public DeleteSavedNewsUseCase provideDeleteSavedNewsUseCase(NewsRepository newsRepository)
    {
        return new DeleteSavedNewsUseCase(newsRepository);
    }
}

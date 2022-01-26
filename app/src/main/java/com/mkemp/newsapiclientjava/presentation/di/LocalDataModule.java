package com.mkemp.newsapiclientjava.presentation.di;

import com.mkemp.newsapiclientjava.data.db.ArticleDAO;
import com.mkemp.newsapiclientjava.data.repository.dataSource.NewsLocalDataSource;
import com.mkemp.newsapiclientjava.data.repository.dataSourceImpl.NewsLocalDataSourceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LocalDataModule
{
    @Singleton
    @Provides
    public NewsLocalDataSource provideLocalDataSource(ArticleDAO articleDAO)
    {
        return new NewsLocalDataSourceImpl(articleDAO);
    }
}

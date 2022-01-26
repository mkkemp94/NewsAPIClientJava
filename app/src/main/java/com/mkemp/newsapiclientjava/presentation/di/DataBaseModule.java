package com.mkemp.newsapiclientjava.presentation.di;

import android.app.Application;

import com.mkemp.newsapiclientjava.data.db.ArticleDAO;
import com.mkemp.newsapiclientjava.data.db.ArticleDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule
{
    @Singleton
    @Provides
    public ArticleDatabase provideArticleDatabase(Application application)
    {
        return Room.databaseBuilder(application, ArticleDatabase.class, "news_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public ArticleDAO provideNewsDao(ArticleDatabase articleDatabase)
    {
        return articleDatabase.getArticleDAO();
    }
}

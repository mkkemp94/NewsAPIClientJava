package com.mkemp.newsapiclientjava.data.db;

import com.mkemp.newsapiclientjava.data.model.Article;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {Article.class},
        version = 1,
        exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class ArticleDatabase extends RoomDatabase
{
    public abstract ArticleDAO getArticleDAO();
}

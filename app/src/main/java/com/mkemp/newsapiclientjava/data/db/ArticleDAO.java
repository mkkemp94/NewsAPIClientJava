package com.mkemp.newsapiclientjava.data.db;

import com.mkemp.newsapiclientjava.data.model.Article;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface ArticleDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article article);
}

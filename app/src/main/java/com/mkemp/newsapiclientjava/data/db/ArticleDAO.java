package com.mkemp.newsapiclientjava.data.db;

import com.mkemp.newsapiclientjava.data.model.Article;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ArticleDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article article);

    @Query("SELECT * FROM articles")
    List<Article> getAllArticles();
}

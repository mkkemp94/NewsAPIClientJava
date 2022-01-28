package com.mkemp.newsapiclientjava.data.repository.dataSource;

import com.mkemp.newsapiclientjava.data.model.Article;

import java.util.List;

public interface NewsLocalDataSource
{
    void saveArticleToDB(Article article);

    List<Article> getSavedArticles();

    void deleteArticleFromDB(Article article);
}

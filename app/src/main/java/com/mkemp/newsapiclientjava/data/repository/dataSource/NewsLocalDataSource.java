package com.mkemp.newsapiclientjava.data.repository.dataSource;

import com.mkemp.newsapiclientjava.data.model.Article;

public interface NewsLocalDataSource
{
    void saveArticleToDB(Article article);
}

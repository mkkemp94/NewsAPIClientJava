package com.mkemp.newsapiclientjava.data.repository.dataSourceImpl;

import com.mkemp.newsapiclientjava.data.db.ArticleDAO;
import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.data.repository.dataSource.NewsLocalDataSource;

import java.util.List;

public class NewsLocalDataSourceImpl implements NewsLocalDataSource
{
    private final ArticleDAO articleDAO;

    public NewsLocalDataSourceImpl(ArticleDAO articleDAO)
    {
        this.articleDAO = articleDAO;
    }

    @Override
    public void saveArticleToDB(final Article article)
    {
        articleDAO.insert(article);
    }

    @Override
    public List<Article> getSavedArticles()
    {
        return articleDAO.getAllArticles();
    }

    @Override
    public void deleteArticleFromDB(final Article article)
    {
        articleDAO.deleteArticle(article);
    }
}

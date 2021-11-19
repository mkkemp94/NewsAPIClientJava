package com.mkemp.newsapiclientjava.domain.repository;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.data.util.Resource;

import java.util.List;

public interface NewsRepository
{
    Resource<APIResponse> getNewsHeadlines(final String country,
                                           final int page);

    Resource<APIResponse> getSearchedNews(final String searchQuery);

    void saveNews(final Article article);

    void deleteNews(final Article article);

    interface SavedNewsHolder
    {
        void gotSavedNews(final List<Article> news);
    }

    void getSavedNews(final Article article, final SavedNewsHolder savedNewsHolder);
}

package com.mkemp.newsapiclientjava.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse
{
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;

    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;

    public APIResponse(final String status, final Integer totalResults, final List<Article> articles)
    {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Integer getTotalResults()
    {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults)
    {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles()
    {
        return articles;
    }

    public void setArticles(List<Article> articles)
    {
        this.articles = articles;
    }
}
package com.mkemp.newsapiclientjava.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.databinding.NewsListItemBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>
{
    public final AsyncListDiffer<Article> differ;

    private OnItemClickListener onItemClickListener;

    public NewsAdapter()
    {
        final DiffUtil.ItemCallback<Article> callback = new DiffUtil.ItemCallback<Article>()
        {
            @Override
            public boolean areItemsTheSame(@NonNull final Article oldItem, @NonNull final Article newItem)
            {
                return oldItem.getUrl().equals(newItem.getUrl());
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull final Article oldItem, @NonNull final Article newItem)
            {
                return oldItem == newItem;
            }
        };

        differ = new AsyncListDiffer<>(this, callback);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType)
    {
        final NewsListItemBinding binding = NewsListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position)
    {
        final Article article = differ.getCurrentList().get(position);
        holder.bind(article);
    }

    @Override
    public int getItemCount()
    {
        return differ.getCurrentList().size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder
    {
        @NonNull private final NewsListItemBinding binding;

        public NewsViewHolder(@NonNull NewsListItemBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final Article article)
        {
            binding.tvTitle.setText(article.getTitle());
            binding.tvDescription.setText(article.getDescription());
            binding.tvPublishedAt.setText(article.getPublishedAt());
            binding.tvSource.setText(article.getSource().getName());

            Glide.with(binding.ivArticleImage.getContext()).
                    load(article.getUrlToImage()).
                    into(binding.ivArticleImage);

            binding.getRoot().setOnClickListener(v -> onItemClickListener.onClick(article));
        }
    }

    public interface OnItemClickListener
    {
        void onClick(Article article);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
}

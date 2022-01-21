package com.mkemp.newsapiclientjava;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.databinding.FragmentNewsBinding;
import com.mkemp.newsapiclientjava.presentation.adapter.NewsAdapter;
import com.mkemp.newsapiclientjava.presentation.viewmodel.NewsViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment
{
    private NewsViewModel newsViewModel;
    private NewsAdapter newsAdapter;
    private FragmentNewsBinding fragmentNewsBinding;
    private String country = "us";
    private int page = 1;

    public NewsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        fragmentNewsBinding = FragmentNewsBinding.bind(view);
        newsViewModel = ((MainActivity) getActivity()).viewModel;
        initRecyclerView();
        viewNewsList();
    }

    private void viewNewsList()
    {
        newsViewModel.getNewsHeadLines(country, page);
        newsViewModel.newsHeadLines.observe(getViewLifecycleOwner(), response ->
        {
            switch (response.status)
            {
                case SUCCESS:
                {
                    hideProgressBar();

                    final APIResponse data = response.data;
                    if (data != null)
                    {
                        newsAdapter.differ.submitList(data.getArticles());
                    }
                }
                case ERROR:
                {
                    hideProgressBar();

                    final String message = response.getMessage();
                    if (message != null)
                    {
                        Log.e("Error", message);
                        Toast.makeText(getActivity(), "An error occurred: " + message, Toast.LENGTH_LONG).show();
                    }
                }
                case LOADING:
                {
                    showProgressBar();
                }
            }
        });
    }

    private void initRecyclerView()
    {
        newsAdapter = new NewsAdapter();
        fragmentNewsBinding.rvNews.setAdapter(newsAdapter);
        fragmentNewsBinding.rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void showProgressBar()
    {
        fragmentNewsBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        fragmentNewsBinding.progressBar.setVisibility(View.INVISIBLE);
    }
}
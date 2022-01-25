package com.mkemp.newsapiclientjava;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.mkemp.newsapiclientjava.data.model.APIResponse;
import com.mkemp.newsapiclientjava.databinding.FragmentNewsBinding;
import com.mkemp.newsapiclientjava.presentation.adapter.NewsAdapter;
import com.mkemp.newsapiclientjava.presentation.viewmodel.NewsViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment
{
    private NewsViewModel viewModel;
    private NewsAdapter newsAdapter;
    private FragmentNewsBinding fragmentNewsBinding;
    private String country = "us";
    private int page = 1;
    private boolean isScrolling = false;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int pages = 0;

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
        viewModel = ((MainActivity) getActivity()).viewModel;
        newsAdapter = ((MainActivity) getActivity()).newsAdapter;

        final NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        newsAdapter.setOnItemClickListener(article ->
        {
            final Bundle bundle = new Bundle();
            bundle.putSerializable("selected_article", article);

            final NavController navController = navHostFragment.getNavController();
            navController.navigate(R.id.action_newsFragment_to_infoFragment, bundle);
        });

        initRecyclerView();
        viewNewsList();
    }

    private void viewNewsList()
    {
        viewModel.getNewsHeadLines(country, page);
        viewModel.newsHeadLines.observe(getViewLifecycleOwner(), response ->
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
                        if (data.getTotalResults() / 20 == 0)
                        {
                            pages = data.getTotalResults() / 20;
                        }
                        else
                        {
                            pages = data.getTotalResults() / 20 + 1;
                        }
                        isLastPage = page == pages;
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
        fragmentNewsBinding.rvNews.setAdapter(newsAdapter);
        fragmentNewsBinding.rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentNewsBinding.rvNews.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                final LinearLayoutManager layoutManager = (LinearLayoutManager) fragmentNewsBinding.rvNews.getLayoutManager();
                final int sizeOfCurrentList = layoutManager.getItemCount();
                final int visibleItems = layoutManager.getChildCount();
                final int topPosition = layoutManager.findFirstVisibleItemPosition();
                final boolean hasReachedEnd = topPosition + visibleItems >= sizeOfCurrentList;
                final boolean shouldPaginate =
//                        ! isLoading &&
                                ! isLastPage && hasReachedEnd && isScrolling;

                if (shouldPaginate)
                {
                    page++;
                    viewModel.getNewsHeadLines(country, page);
                    isScrolling = false;
                }
            }
        });
    }

    private void showProgressBar()
    {
        isLoading = true;
        fragmentNewsBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        isLoading = false;
        fragmentNewsBinding.progressBar.setVisibility(View.INVISIBLE);
    }
}
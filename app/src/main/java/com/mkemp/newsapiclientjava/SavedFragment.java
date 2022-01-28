package com.mkemp.newsapiclientjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.databinding.FragmentSavedBinding;
import com.mkemp.newsapiclientjava.presentation.adapter.NewsAdapter;
import com.mkemp.newsapiclientjava.presentation.viewmodel.NewsViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.ItemTouchHelper.DOWN;
import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
import static androidx.recyclerview.widget.ItemTouchHelper.UP;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SavedFragment extends Fragment
{
    private FragmentSavedBinding fragmentSavedBinding;
    private NewsViewModel viewModel;
    private NewsAdapter newsAdapter;

    public SavedFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        fragmentSavedBinding = FragmentSavedBinding.bind(view);
        viewModel = ((MainActivity) getActivity()).viewModel;
        newsAdapter = ((MainActivity) getActivity()).newsAdapter;

        final NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment);
        newsAdapter.setOnItemClickListener(article ->
        {
            final Bundle bundle = new Bundle();
            bundle.putSerializable("selected_article", article);

            final NavController navController = navHostFragment.getNavController();
            navController.navigate(R.id.action_newsFragment_to_infoFragment, bundle);
        });

        initRecyclerView();
        viewModel.savedNews.observe(getViewLifecycleOwner(), articles -> newsAdapter.differ.submitList(articles));
        viewModel.getSavedNews();

        final SimpleCallback itemTouchCallback = new SimpleCallback(UP | DOWN, LEFT | RIGHT)
        {
            @Override
            public boolean onMove(@NonNull final RecyclerView recyclerView, @NonNull final RecyclerView.ViewHolder viewHolder, @NonNull final RecyclerView.ViewHolder target)
            {
                return true;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, final int direction)
            {
                final int position = viewHolder.getAdapterPosition();
                final Article article = newsAdapter.differ.getCurrentList().get(position);
                viewModel.deleteArticle(article);
                newsAdapter.notifyItemRemoved(position);

                final Snackbar snackbar = Snackbar.make(view, "Deleted Successfully", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", (v) ->
                {
                    // TODO This doesn't work right
                    viewModel.saveArticle(article);
                    newsAdapter.notifyItemInserted(newsAdapter.differ.getCurrentList().size());
                });
                snackbar.show();
            }
        };

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(fragmentSavedBinding.rvSaved);
    }

    private void initRecyclerView()
    {
        fragmentSavedBinding.rvSaved.setAdapter(newsAdapter);
        fragmentSavedBinding.rvSaved.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
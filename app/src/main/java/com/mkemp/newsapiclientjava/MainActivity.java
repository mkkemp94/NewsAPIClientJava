package com.mkemp.newsapiclientjava;

import android.os.Bundle;

import com.mkemp.newsapiclientjava.databinding.ActivityMainBinding;
import com.mkemp.newsapiclientjava.presentation.adapter.NewsAdapter;
import com.mkemp.newsapiclientjava.presentation.viewmodel.NewsViewModel;
import com.mkemp.newsapiclientjava.presentation.viewmodel.NewsViewModelFactory;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Project Use Cases:
 * - Get news headlines (main)
 * - Save favorite news
 * - Get saved news
 * - Delete saved news
 * - Search
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity
{
    @Inject
    public NewsViewModelFactory factory;

    @Inject
    public NewsAdapter newsAdapter;

    public NewsViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Hook up bottom navigation view
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        final NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bnvNews, navController);

        viewModel = new ViewModelProvider(this, factory).get(NewsViewModel.class);
    }
}
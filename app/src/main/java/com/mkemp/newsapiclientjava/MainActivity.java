package com.mkemp.newsapiclientjava;

import android.os.Bundle;

import com.mkemp.newsapiclientjava.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

/**
 * Project Use Cases:
 * - Get news headlines (main)
 * - Save favorite news
 * - Get saved news
 * - Delete saved news
 * - Search
 */
public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Hook up bottom navigation view
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        final NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bnvNews, navController);
    }
}
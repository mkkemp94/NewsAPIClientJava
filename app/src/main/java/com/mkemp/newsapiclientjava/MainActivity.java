package com.mkemp.newsapiclientjava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
package com.mkemp.newsapiclientjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import com.mkemp.newsapiclientjava.data.model.Article;
import com.mkemp.newsapiclientjava.databinding.FragmentInfoBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment
{
    private FragmentInfoBinding fragmentInfoBinding;

    public InfoFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        fragmentInfoBinding = FragmentInfoBinding.bind(view);
        final InfoFragmentArgs args = InfoFragmentArgs.fromBundle(getArguments());
        final Article article = args.getSelectedArticle();
        fragmentInfoBinding.wvInfo.setWebViewClient(new WebViewClient());
        if (! article.getUrl().isEmpty())
        {
            fragmentInfoBinding.wvInfo.loadUrl(article.getUrl());
        }

    }
}
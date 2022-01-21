package com.mkemp.newsapiclientjava.presentation.di;

import com.mkemp.newsapiclientjava.BuildConfig;
import com.mkemp.newsapiclientjava.data.api.NewsAPIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetModule
{
    @Singleton
    @Provides
    public Retrofit provideRetrofit()
    {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Singleton
    @Provides
    public NewsAPIService provideNewsAPIService(Retrofit retrofit)
    {
        return retrofit.create(NewsAPIService.class);
    }
}

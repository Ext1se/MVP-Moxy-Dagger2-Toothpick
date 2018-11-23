package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.data.api.ApiKeyInterceptor;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.google.gson.Gson;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toothpick.config.Module;

public class NetworkModule extends Module{

    private final Gson mGson = provideGson();
    private final OkHttpClient mOkHttpClient = provideClient();
    private final Retrofit mRetrofit = provideRetrofit();

    public NetworkModule() {
        bind(Gson.class).toInstance(mGson);
        bind(OkHttpClient.class).toInstance(mOkHttpClient);
        bind(Retrofit.class).toInstance(mRetrofit);
        bind(BehanceApi.class).toInstance(provideApiService());
    }

    public Gson provideGson() {
        return new Gson();
    }

    public OkHttpClient provideClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(new ApiKeyInterceptor());
        if (!BuildConfig.BUILD_TYPE.contains("release")) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    BehanceApi provideApiService() {
            return mRetrofit.create(BehanceApi.class);
    }

}

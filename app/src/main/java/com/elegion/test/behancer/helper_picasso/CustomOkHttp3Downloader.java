package com.elegion.test.behancer.helper_picasso;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.picasso.Downloader;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//https://github.com/JakeWharton/picasso2-okhttp3-downloader/blob/master/src/main/java/com/jakewharton/picasso/OkHttp3Downloader.java

public class CustomOkHttp3Downloader implements Downloader {
    private static final String PICASSO_CACHE = "picasso-mCache";
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024;
    private final Call.Factory mClient;
    private final Cache mCache;

    static File createDefaultCacheDir(Context context) {
        File cache = new File(context.getApplicationContext().getCacheDir(), PICASSO_CACHE);
        if (!cache.exists()) {
            //noinspection ResultOfMethodCallIgnored
            cache.mkdirs();
        }
        return cache;
    }

    public CustomOkHttp3Downloader(OkHttpClient client, Context context) {
        if (client.cache() != null) {
            mCache = client.cache();
        } else {
            mCache = new Cache(createDefaultCacheDir(context), DISK_CACHE_SIZE);
            client = client.newBuilder().cache(mCache).build();
        }
        mClient = client;
    }

    @NonNull
    @Override
    public Response load(@NonNull Request request) throws IOException {
        return mClient.newCall(request).execute();
    }

    @Override
    public void shutdown() {
        if (mCache != null) {
            try {
                mCache.close();
            } catch (IOException ignored) {
            }
        }
    }
}
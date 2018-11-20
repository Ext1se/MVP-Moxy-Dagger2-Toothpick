package com.elegion.test.behancer.helper_picasso;

import android.content.Context;
import android.os.Build;

import com.squareup.picasso.Picasso;

public class PicassoHelper {
    /*
    Для API <= 19 изображения не загружаются с Behance.net. Возникают проблемы с протоколами.
    Поэтому создается кастомный OkHttpClient реализующий протокол TLS,
    и кастомный CustomOkHttp3Downloader, чтобы создать кеш-папку.
    Аналогичная проблема наблюдается и у Glide. Решается она схожим образом.
    */
    public static void createPicasso(Context context) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            Picasso picasso = new Picasso.Builder(context)
                    .downloader(new CustomOkHttp3Downloader(CustomOkHttpClient.getTls12OkHttpClient(), context))
                    .loggingEnabled(true)
                    .indicatorsEnabled(true)
                    .build();

            Picasso.setSingletonInstance(picasso);
        }
    }
}

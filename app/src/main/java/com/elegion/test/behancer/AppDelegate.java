package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.di.component.AppComponent;
import com.elegion.test.behancer.di.module.AppModule;
import com.elegion.test.behancer.di.component.DaggerAppComponent;
import com.elegion.test.behancer.di.module.NetworkModule;
import com.elegion.test.behancer.helper_picasso.PicassoHelper;

public class AppDelegate extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        PicassoHelper.createPicasso(this);
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}

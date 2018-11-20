package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.di.component.AppComponent;
import com.elegion.test.behancer.di.component.ProfileFragmentSingletonSubComponent;
import com.elegion.test.behancer.di.component.ProjectsFragmentSingletonSubComponent;
import com.elegion.test.behancer.di.module.AppModule;
import com.elegion.test.behancer.di.component.DaggerAppComponent;
import com.elegion.test.behancer.di.module.NetworkModule;
import com.elegion.test.behancer.di.module.ProfileFragmentSingletonModule;
import com.elegion.test.behancer.di.module.ProjectsFragmentSingletonModule;
import com.elegion.test.behancer.helper_picasso.PicassoHelper;

public class AppDelegate extends Application {

    private static AppComponent sAppComponent;
    private static ProjectsFragmentSingletonSubComponent sProjectsComponentSingleton;
    private static ProfileFragmentSingletonSubComponent sProfileComponentSingleton;

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

    public static ProjectsFragmentSingletonSubComponent getProjectsSingletonComponent() {
        if (sProjectsComponentSingleton == null) {
            sProjectsComponentSingleton = getAppComponent().plusProjectsFragmentSingleton(new ProjectsFragmentSingletonModule());
        }
        return sProjectsComponentSingleton;
    }

    public static ProfileFragmentSingletonSubComponent getProfileSingletonComponent() {
        if (sProfileComponentSingleton == null) {
            sProfileComponentSingleton = getAppComponent().plusProfileFragmentSingleton(new ProfileFragmentSingletonModule());
        }
        return sProfileComponentSingleton;
    }

    public static void releaseProfileSingletonComponent() {
        sProfileComponentSingleton = null;
    }

    public static void releaseProjectsSingletonComponent() {
        sProjectsComponentSingleton = null;
    }
}

package com.elegion.test.behancer.di.module;

import android.arch.persistence.room.Room;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.database.BehanceDatabase;

import toothpick.config.Module;

public class AppModule extends Module {
    private final AppDelegate mApp;

    public AppModule(AppDelegate app) {
        mApp = app;
        bind(AppDelegate.class).toInstance(mApp);
        bind(Storage.class).toInstance(provideStorage());
    }

    AppDelegate provideApp() {
        return mApp;
    }

    Storage provideStorage() {
        BehanceDatabase database = Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();

        return new Storage(database.getBehanceDao());
    }
}

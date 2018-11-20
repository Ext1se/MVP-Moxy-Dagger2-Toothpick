package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.di.scope.SingletonFragmentScope;
import com.elegion.test.behancer.ui.profile.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileFragmentSingletonModule {
    @SingletonFragmentScope
    @Provides
    ProfilePresenter provideProfilePresenter(Storage storage, BehanceApi api) {
        return new ProfilePresenter(storage, api);
    }
}

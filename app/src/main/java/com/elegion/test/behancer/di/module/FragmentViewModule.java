package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class FragmentViewModule {

    protected PresenterFragment mFragment;

    public FragmentViewModule(PresenterFragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    RefreshOwner provideOwner() {
        if (mFragment.getContext() instanceof RefreshOwner) {
            return (RefreshOwner) mFragment.getContext();
        }
        return null;
    }
}

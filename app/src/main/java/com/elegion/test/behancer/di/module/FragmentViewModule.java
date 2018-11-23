package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import toothpick.config.Module;

public class FragmentViewModule extends Module {

    protected PresenterFragment mFragment;

    public FragmentViewModule(PresenterFragment fragment) {
        mFragment = fragment;
        bind(RefreshOwner.class).toInstance(provideOwner());
    }

    RefreshOwner provideOwner() {
        if (mFragment.getContext() instanceof RefreshOwner) {
            return (RefreshOwner) mFragment.getContext();
        }
        return null;
    }
}

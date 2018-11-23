package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.di.provider.ProfilePresenterProvider;
import com.elegion.test.behancer.di.provider.ProjectsPresenterProvider;
import com.elegion.test.behancer.ui.profile.ProfilePresenter;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;

import toothpick.config.Module;

public class SingletonProfileFragmentModule extends Module {

    public SingletonProfileFragmentModule() {
        bind(ProfilePresenter.class).toProvider(ProfilePresenterProvider.class).providesSingletonInScope();
    }
}

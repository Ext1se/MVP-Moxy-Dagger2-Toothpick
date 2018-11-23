package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.di.provider.ProjectsPresenterProvider;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;

import toothpick.config.Module;

public class ProfilePresenterModule extends Module {

    public ProfilePresenterModule() {
        bind(ProjectsPresenter.class).toProvider(ProjectsPresenterProvider.class).providesSingletonInScope();
    }
}

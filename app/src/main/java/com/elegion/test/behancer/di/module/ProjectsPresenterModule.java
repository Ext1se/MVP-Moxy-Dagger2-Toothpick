package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.di.provider.ProjectsPresenterProvider;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;
import com.elegion.test.behancer.ui.projects.ProjectsView;

import toothpick.config.Module;

public class ProjectsPresenterModule extends Module {

    public ProjectsPresenterModule() {
        bind(ProjectsPresenter.class).toProvider(ProjectsPresenterProvider.class).providesSingletonInScope();
    }
}

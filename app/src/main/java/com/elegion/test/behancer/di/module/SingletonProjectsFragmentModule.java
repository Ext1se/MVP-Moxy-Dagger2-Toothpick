package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.di.provider.ProjectsPresenterProvider;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;

import toothpick.config.Module;

public class SingletonProjectsFragmentModule extends Module {

    public SingletonProjectsFragmentModule() {
        bind(ProjectsPresenter.class).toProvider(ProjectsPresenterProvider.class).providesSingletonInScope();
        bind(ProjectsAdapter.class).toInstance(provideProjectsAdapter());
    }

    ProjectsAdapter provideProjectsAdapter() {
        return new ProjectsAdapter();
    }
}

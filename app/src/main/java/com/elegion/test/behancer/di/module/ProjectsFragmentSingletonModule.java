package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.di.scope.SingletonFragmentScope;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProjectsFragmentSingletonModule {
    @SingletonFragmentScope
    @Provides
    ProjectsAdapter provideAdapter() {
        return new ProjectsAdapter();
    }

    @SingletonFragmentScope
    @Provides
    ProjectsPresenter provideProjectsPresenter(Storage storage, BehanceApi api) {
        return new ProjectsPresenter(storage, api);
    }
}

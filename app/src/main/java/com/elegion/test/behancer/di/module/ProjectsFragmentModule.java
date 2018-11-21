package com.elegion.test.behancer.di.module;

import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.di.scope.FragmentScope;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;
import com.elegion.test.behancer.ui.projects.ProjectsView;

import dagger.Module;
import dagger.Provides;

@Module
public class ProjectsFragmentModule extends FragmentViewModule {

    public ProjectsFragmentModule(ProjectsFragment fragment) {
        super(fragment);
    }

    @FragmentScope
    @Provides
    ProjectsPresenter provideProjectsPresenter(Storage storage, BehanceApi api) {
        return new ProjectsPresenter(storage, api);
    }
}

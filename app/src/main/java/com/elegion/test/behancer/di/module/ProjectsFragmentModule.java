package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.di.scope.FragmentScope;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;
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
    ProjectsView provideProjectsView() {
        return (ProjectsView) mFragment;
    }
}

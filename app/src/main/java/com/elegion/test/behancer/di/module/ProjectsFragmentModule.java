package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.di.scope.FragmentScope;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;
import com.elegion.test.behancer.ui.projects.ProjectsView;

public class ProjectsFragmentModule extends FragmentViewModule {

    public ProjectsFragmentModule(ProjectsFragment fragment) {
        super(fragment);
        bind(ProjectsView.class).toInstance(provideProjectsView());
    }

    ProjectsView provideProjectsView() {
        return (ProjectsView) mFragment;
    }
}

package com.elegion.test.behancer.di.component;

import com.elegion.test.behancer.di.module.ProjectsFragmentModule;
import com.elegion.test.behancer.di.module.ProjectsFragmentSingletonModule;
import com.elegion.test.behancer.di.scope.SingletonFragmentScope;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;

import dagger.Subcomponent;

@SingletonFragmentScope
@Subcomponent(modules = {ProjectsFragmentSingletonModule.class})
public interface ProjectsFragmentSingletonSubComponent {
    ProjectsFragmentSubComponent plusProjectsFragment(ProjectsFragmentModule module);
}

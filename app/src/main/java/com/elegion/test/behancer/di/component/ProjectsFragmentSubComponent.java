package com.elegion.test.behancer.di.component;

import com.elegion.test.behancer.di.module.ProjectsFragmentModule;
import com.elegion.test.behancer.di.scope.FragmentScope;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {ProjectsFragmentModule.class})
public interface ProjectsFragmentSubComponent extends FragmentComponent<ProjectsFragment> {

}

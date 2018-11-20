package com.elegion.test.behancer.di.component;

import com.elegion.test.behancer.di.module.AppModule;
import com.elegion.test.behancer.di.module.NetworkModule;
import com.elegion.test.behancer.di.module.ProfileFragmentModule;
import com.elegion.test.behancer.di.module.ProfileFragmentSingletonModule;
import com.elegion.test.behancer.di.module.ProjectsFragmentModule;
import com.elegion.test.behancer.di.module.ProjectsFragmentSingletonModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    ProfileFragmentSingletonSubComponent plusProfileFragmentSingleton(ProfileFragmentSingletonModule module);
    ProjectsFragmentSingletonSubComponent plusProjectsFragmentSingleton(ProjectsFragmentSingletonModule module);
}

package com.elegion.test.behancer.di.component;

import com.elegion.test.behancer.di.module.ProfileFragmentModule;
import com.elegion.test.behancer.di.module.ProfileFragmentSingletonModule;
import com.elegion.test.behancer.di.scope.FragmentScope;
import com.elegion.test.behancer.di.scope.SingletonFragmentScope;
import com.elegion.test.behancer.ui.profile.ProfileFragment;

import dagger.Subcomponent;

@SingletonFragmentScope
@Subcomponent(modules = {ProfileFragmentSingletonModule.class})
public interface ProfileFragmentSingletonSubComponent {
    ProfileFragmentSubComponent plusProfileFragment(ProfileFragmentModule module);
}

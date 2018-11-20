package com.elegion.test.behancer.di.component;

import com.elegion.test.behancer.di.module.ProfileFragmentModule;
import com.elegion.test.behancer.di.scope.FragmentScope;
import com.elegion.test.behancer.ui.profile.ProfileFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {ProfileFragmentModule.class})
public interface ProfileFragmentSubComponent extends FragmentComponent<ProfileFragment> {

}

package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.di.scope.FragmentScope;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfileView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

@Module
public class ProfileFragmentModule extends FragmentViewModule {

    public ProfileFragmentModule(ProfileFragment fragment) {
        super(fragment);
    }

    @FragmentScope
    @Provides
    ProfileView provideProfileView() {
        return (ProfileView) mFragment;
    }

    @FragmentScope
    @Provides
    @Named(PROFILE_KEY)
    String provideOwnerName() {
        if (mFragment.getArguments() != null) {
            return mFragment.getArguments().getString(PROFILE_KEY);
        }
        return null;
    }
}

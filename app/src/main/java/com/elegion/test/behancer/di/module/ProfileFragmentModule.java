package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfileView;

import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

public class ProfileFragmentModule extends FragmentViewModule {

    public ProfileFragmentModule(ProfileFragment fragment) {
        super(fragment);
        bind(ProfileView.class).toInstance(provideProfileView());
        bind(String.class).withName(PROFILE_KEY).toInstance(provideOwnerName());
    }

    ProfileView provideProfileView() {
        return (ProfileView) mFragment;
    }

    String provideOwnerName() {
        if (mFragment.getArguments() != null) {
            return mFragment.getArguments().getString(PROFILE_KEY);
        }
        return null;
    }
}

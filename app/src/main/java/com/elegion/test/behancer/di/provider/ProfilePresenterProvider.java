package com.elegion.test.behancer.di.provider;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.profile.ProfilePresenter;
import com.elegion.test.behancer.ui.profile.ProfileView;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;
import com.elegion.test.behancer.ui.projects.ProjectsView;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

public class ProfilePresenterProvider implements Provider<ProfilePresenter> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    @Inject
    BaseView mView;
    @Inject
    @Named(PROFILE_KEY)
    String mName;

    @Override
    public ProfilePresenter get() {
        return new ProfilePresenter(mStorage, mApi, (ProfileView)mView, mName);
    }
}

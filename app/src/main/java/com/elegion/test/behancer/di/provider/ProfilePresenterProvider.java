package com.elegion.test.behancer.di.provider;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.profile.ProfilePresenter;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProfilePresenterProvider implements Provider<ProfilePresenter> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;

    @Override
    public ProfilePresenter get() {
        return new ProfilePresenter(mStorage, mApi);
    }
}

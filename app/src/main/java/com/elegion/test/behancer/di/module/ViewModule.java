package com.elegion.test.behancer.di.module;

import android.support.v4.app.Fragment;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.ui.profile.ProfileView;
import com.elegion.test.behancer.ui.projects.ProjectsView;

import toothpick.config.Module;

import static com.elegion.test.behancer.ui.profile.ProfileFragment.PROFILE_KEY;

public class ViewModule extends Module {

    private BaseView mBaseView;

    public ViewModule(BaseView baseView) {
        mBaseView = baseView;
        bind(BaseView.class).toInstance(provideView());
        if ((mBaseView instanceof Fragment) && ((Fragment) mBaseView).getArguments() != null) {
            bind(String.class).withName(PROFILE_KEY).toInstance(provideOwnerName());
        }
    }

    BaseView provideView() {
        return mBaseView;
    }

    String provideOwnerName() {
        return ((Fragment) mBaseView).getArguments().getString(PROFILE_KEY);
    }
}

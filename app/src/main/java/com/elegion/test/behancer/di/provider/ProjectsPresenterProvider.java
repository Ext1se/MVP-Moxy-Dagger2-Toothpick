package com.elegion.test.behancer.di.provider;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;
import com.elegion.test.behancer.ui.projects.ProjectsView;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProjectsPresenterProvider implements Provider<ProjectsPresenter> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;
    @Inject
    BaseView mView;

    @Override
    public ProjectsPresenter get() {
        return new ProjectsPresenter(mStorage, mApi, (ProjectsView)mView);
    }
}

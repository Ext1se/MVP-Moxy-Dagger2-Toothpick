package com.elegion.test.behancer.ui.projects;

import android.support.annotation.Keep;
import android.view.View;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.profile.ProfileView;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectsPresenter extends BasePresenter {

    private Storage mStorage;
    private BehanceApi mApi;
    private ProjectsView mView;
    private boolean mIsLoadedData;

    @Inject
    public ProjectsPresenter(Storage storage, BehanceApi api) {
        mStorage = storage;
        mApi = api;
    }

    public void setView(ProjectsView view) {
        mView = view;
        if (!mIsLoadedData) {
            mIsLoadedData = true;
            getProjects();
        }
    }

    public void getProjects() {
        mCompositeDisposable.add(mApi.getProjects(BuildConfig.API_QUERY)
                .doOnSuccess(response -> {
                    mNetworkConnection = true;
                    mStorage.insertProjects(response);
                })
                .onErrorReturn(throwable ->
                {
                    mNetworkConnection = false;
                    if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())) {
                        return mStorage.getProjects();
                    }
                    return null;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> {
                    mView.hideRefresh();
                    mView.showMessage(mNetworkConnection);
                })
                .subscribe(
                        response -> mView.showProjects(response.getProjects()),
                        throwable -> mView.showError()));
    }

    public void openProfileFragment(String username) {
        mView.openProfileFragment(username);
    }
}

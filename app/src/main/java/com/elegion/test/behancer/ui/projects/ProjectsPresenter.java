package com.elegion.test.behancer.ui.projects;

import android.support.annotation.Keep;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    private Storage mStorage;
    private BehanceApi mApi;

    @Inject
    public ProjectsPresenter(Storage storage, BehanceApi api) {
        mStorage = storage;
        mApi = api;
        getProjects();
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
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> {
                    getViewState().hideRefresh();
                    getViewState().showMessage(mNetworkConnection);
                })
                .subscribe(
                        response -> getViewState().showProjects(response.getProjects()),
                        throwable -> getViewState().showError()));
    }

    public void openProfileFragment(String username) {
        getViewState().openProfileFragment(username);
    }
}

package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter extends BasePresenter {

    private Storage mStorage;
    private BehanceApi mApi;
    private ProfileView mView;

    @Inject
    public ProfilePresenter(Storage storage, BehanceApi api) {
        mStorage = storage;
        mApi = api;
    }

    public void setView(ProfileView view) {
        mView = view;
    }

    public void getProfile(String username) {
        mCompositeDisposable.add(mApi.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> {
                    mNetworkConnection = true;
                    mStorage.insertUser(response);
                })
                .onErrorReturn(throwable -> {
                    mNetworkConnection = false;
                    if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())) {
                        return mStorage.getUser(username);
                    }
                    return null;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> {
                    mView.hideRefresh();
                    mView.showMessage(mNetworkConnection);
                })
                .subscribe(
                        response -> mView.showProfile(response.getUser()),
                        throwable -> mView.showError()));
    }
}

package com.elegion.test.behancer.ui.profile;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView> {

    private Storage mStorage;
    private BehanceApi mApi;

    public ProfilePresenter(Storage storage, BehanceApi api) {
        mStorage = storage;
        mApi = api;
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
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> {
                    getViewState().hideRefresh();
                    getViewState().showMessage(mNetworkConnection);
                })
                .subscribe(
                        response -> getViewState().showProfile(response.getUser()),
                        throwable -> getViewState().showError()));
    }
}

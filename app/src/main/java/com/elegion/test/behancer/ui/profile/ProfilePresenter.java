package com.elegion.test.behancer.ui.profile;

import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter extends BasePresenter {

    private Storage mStorage;
    private BehanceApi mApi;
    private ProfileView mView;
    private User mUser;
    private boolean mIsLoadedData = false;

    @Inject
    public ProfilePresenter(Storage storage, BehanceApi api) {
        mStorage = storage;
        mApi = api;
    }

    public void setView(ProfileView view, String username) {
        mView = view;
        if (!mIsLoadedData) {
            mIsLoadedData = true;
            getProfile(username);
        } else {
            if (mUser != null) {
                view.showProfile(mUser);
            } else {
                getProfile(username);
            }
        }
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
                        response -> {
                            mUser = response.getUser();
                            mView.showProfile(mUser);
                        },
                        throwable -> mView.showError()));
    }
}

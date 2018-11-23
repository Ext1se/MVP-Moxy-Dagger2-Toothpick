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

    @Inject
    public ProfilePresenter(Storage storage, BehanceApi api, ProfileView view, String username) {
        mStorage = storage;
        mApi = api;
        mView = view;
        getProfile(username);
    }

    public void setView(ProfileView view) {
        mView = view;
        if (mUser != null) {
            mView.showProfile(mUser);
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

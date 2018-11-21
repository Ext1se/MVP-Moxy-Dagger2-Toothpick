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


/*
    Интересное наблюдение:
    Вызов метода (например, getProfile), который взаимодействует с нашей View, в конструкторе опасен тем,
    что компоненты View могут быть еще не проинициализированы.
    Поэтому без использования Moxy нужно быть аккуратным с вызовом конструктора.
    Сопоставлять жизненные циклы наших зависимостей и фрагмента.
    НО!
    В случаи с Moxy. С этим можно не беспокоиться. Все действия, которые совершаем с View,
    выполнятся тогда, когда будет создана View со всеми ее компонентами. Скорее всего
    как минимум после onActivityCreated согласно дебаггингу.
*/

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;

    public ProfilePresenter(String username) {
        AppDelegate.getAppComponent().inject(this);
        getProfile(username);
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

package com.elegion.test.behancer.ui.projects;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.utils.ApiUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
    Интересное наблюдение:
    Вызов метода (например, getProjects), который взаимодействует с нашей View, из конструктора опасен тем,
    что компоненты View могут быть еще не проинициализированы.
    Поэтому без использования Moxy нужно быть аккуратным с вызовом конструктора.
    Сопоставлять жизненные циклы наших зависимостей и фрагмента.
    НО!
    В случаи с Moxy. С этим можно не беспокоиться. Все действия, которые совершаем с View,
    выполнятся тогда, когда будет создана View со всеми ее компонентами. Скорее всего
    как минимум после onActivityCreated согласно дебаггингу.
*/

@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    @Inject
    Storage mStorage;
    @Inject
    BehanceApi mApi;

    public ProjectsPresenter() {
        AppDelegate.getAppComponent().inject(this);
        getProjects();
    }

    public void getProjects() {
        mCompositeDisposable.add(
                mApi.getProjects(BuildConfig.API_QUERY)
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

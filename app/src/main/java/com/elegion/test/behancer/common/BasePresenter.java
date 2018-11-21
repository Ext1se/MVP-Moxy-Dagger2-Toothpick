package com.elegion.test.behancer.common;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter <View extends BaseView> extends MvpPresenter<View> {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    protected boolean mNetworkConnection = false;

    public void disposeAll()
    {
        mCompositeDisposable.clear();
    }
}

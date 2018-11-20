package com.elegion.test.behancer.common;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    protected boolean mNetworkConnection = false;

    public void disposeAll()
    {
        mCompositeDisposable.clear();
    }
}

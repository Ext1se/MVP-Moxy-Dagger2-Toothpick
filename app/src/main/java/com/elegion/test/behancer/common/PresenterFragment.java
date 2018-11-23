package com.elegion.test.behancer.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;

public abstract class PresenterFragment extends MvpAppCompatFragment {

    protected abstract BasePresenter getPresenter();

    @Override
    public void onDetach() {
        if (getPresenter() != null) {
            getPresenter().disposeAll();
        }
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Override
    public void onDestroy() {
        clearDependencies();
        super.onDestroy();
    }

    protected abstract void injectDependencies();
    protected abstract void clearDependencies();

    public abstract void setDependencies();
}

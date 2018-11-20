package com.elegion.test.behancer.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.di.component.AppComponent;

import javax.inject.Inject;

public abstract class PresenterFragment<P extends BasePresenter> extends Fragment {

    protected abstract P getPresenter();

    @Override
    public void onDetach() {
        if (getPresenter() != null) {
            getPresenter().disposeAll();
        }
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    protected abstract void injectDependencies();

    public abstract void setDependencies();
}

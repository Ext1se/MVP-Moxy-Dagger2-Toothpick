package com.elegion.test.behancer.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class PresenterFragment<P extends BasePresenter> extends Fragment {

    protected abstract P getPresenter();

    @Override
    public void onDetach() {
        if (getPresenter() != null) {
            getPresenter().disposeAll();
        }
        super.onDetach();
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies();
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectDependencies();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearDependencies();
    }

    protected abstract void injectDependencies();
    protected abstract void clearDependencies();

    public abstract void setDependencies();
}

package com.elegion.test.behancer.common;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface BaseView extends MvpView{

    void showRefresh();

    void hideRefresh();

    void showMessage(boolean networkConnection);

    void showError();
}

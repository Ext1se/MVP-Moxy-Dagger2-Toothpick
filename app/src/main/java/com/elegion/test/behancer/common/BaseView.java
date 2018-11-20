package com.elegion.test.behancer.common;

public interface BaseView {

    void showRefresh();

    void hideRefresh();

    void showMessage(boolean networkConnection);

    void showError();
}

package com.elegion.test.behancer.ui.profile;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.user.User;

public interface ProfileView extends BaseView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    public void showProfile(User user);
}

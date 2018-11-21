package com.elegion.test.behancer.ui.projects;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.project.Project;

import java.util.List;

public interface ProjectsView extends BaseView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProjects(List<Project> project);

    @StateStrategyType(SkipStrategy.class)
    void openProfileFragment(String username);
}

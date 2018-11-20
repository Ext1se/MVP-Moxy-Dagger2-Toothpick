package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.data.model.project.Project;

import java.util.List;

public interface ProjectsView extends BaseView {

    void showProjects(List<Project> project);

    void openProfileFragment(String username);
}

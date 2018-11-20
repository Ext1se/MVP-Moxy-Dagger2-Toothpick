package com.elegion.test.behancer.ui.projects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.R;
import com.elegion.test.behancer.data.model.project.Project;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsHolder> {

    @NonNull
    private List<Project> mProjects = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    @Inject
    public ProjectsAdapter() {

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_projects, parent, false);
        return new ProjectsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsHolder holder, int position) {
        Project project = mProjects.get(position);
        holder.bind(project, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public void addData(List<Project> data, boolean isRefreshed) {
        if (isRefreshed) {
            mProjects.clear();
        }

        if (data != null && data.size() != 0) {
            mProjects.addAll(data);
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String username);
    }
}

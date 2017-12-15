package com.example.ruslanio.experienceexchange.utils.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public class MainRecyclerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}

package com.example.ruslanio.experienceexchange.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ruslanio.experienceexchange.interfaces.presenter.BasePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.BaseViewInterface;

import butterknife.ButterKnife;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public abstract class BaseFragment<T extends BasePresenterInterface> extends Fragment implements BaseViewInterface {


    protected T mPresenter;
    protected abstract T getPresenter();
    @LayoutRes
    protected abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(),container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInit();
        mPresenter.onInit(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null)
            mPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null)
            mPresenter = getPresenter();
        mPresenter.onCreate(savedInstanceState);
    }

    protected void onInit() {
    }

    public void showToast(@StringRes int text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showSnackbar(@StringRes int text) {
        Snackbar.make(getActivity().getWindow().getDecorView(), text, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    public void showSnackbar(String text) {
        Snackbar.make(getActivity().getWindow().getDecorView(), text, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroy();
    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }
}

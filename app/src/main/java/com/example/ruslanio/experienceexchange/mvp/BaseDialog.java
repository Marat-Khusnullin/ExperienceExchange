package com.example.ruslanio.experienceexchange.mvp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.BasePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.BaseViewInterface;
import com.example.ruslanio.experienceexchange.utils.managers.DialogManager;

import butterknife.ButterKnife;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public abstract class BaseDialog<T extends BasePresenterInterface> extends DialogFragment implements BaseViewInterface ,BaseConstants{
    protected T mPresenter;
    private DialogManager mDialogManager;


    protected abstract T getPresenter();

    @LayoutRes
    protected abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null)
            mPresenter = getPresenter();
        mDialogManager = new DialogManager(this);
        mPresenter.onCreate(savedInstanceState);
    }



    protected void onInit() {

    }

    //After presenter
    protected void onPostInit() {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInit();
        mPresenter.onInit(savedInstanceState);
        onPostInit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null)
            mPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().getAttributes().windowAnimations = R.style.AnimationDialog;
        return dialog;
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
    }

    @Override
    public DialogFragment addDialog(DialogFragment dialogFragment, String tag) {
        return mDialogManager.addDialog(dialogFragment, tag);
    }

    @Override
    public void dismissDialog(String tag) {
        mDialogManager.dismissDialog(tag);
    }

    @Override
    public void showDialog(String tag) {
        mDialogManager.showDialog(tag);
    }

    @Override
    public void hideDialog(String tag) {
        mDialogManager.hideDialog(tag);
    }

    @Override
    public void showToast(@StringRes int text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnackbar(@StringRes int text) {
        Snackbar.make(getActivity().getWindow().getDecorView(), text, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showSnackbar(String text) {
        Snackbar.make(getActivity().getWindow().getDecorView(), text, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    protected void dismissSelf(boolean allowStateLoss){
        if (allowStateLoss)
            this.dismissAllowingStateLoss();
        else
            this.dismiss();
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

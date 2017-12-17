package com.example.ruslanio.experienceexchange.utils.managers;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.ruslanio.experienceexchange.utils.managers.interfaces.DialogManagerInterface;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class DialogManager implements DialogManagerInterface {
    private FragmentManager mFragmentManager;

    public DialogManager(AppCompatActivity appCompatActivity) {
        mFragmentManager = appCompatActivity.getSupportFragmentManager();
    }

    public DialogManager(Fragment fragment) {
        mFragmentManager = fragment.getFragmentManager();
    }

    @Override
    public DialogFragment addDialog(DialogFragment dialogFragment, String tag) {
        DialogFragment fragment = (DialogFragment) mFragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = dialogFragment;
        }
        if (!fragment.isAdded()) {
            fragment.show(mFragmentManager, tag);
        }
        return fragment;
    }

    @Override
    public void dismissDialog(String tag) {
        DialogFragment dialogFragment = (DialogFragment) mFragmentManager.findFragmentByTag(tag);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    @Override
    public void hideDialog(String tag) {
        DialogFragment dialogFragment = (DialogFragment) mFragmentManager.findFragmentByTag(tag);
        if (dialogFragment != null)
            mFragmentManager
                    .beginTransaction()
                    .detach(dialogFragment)
                    .commit();

    }

    @Override
    public void showDialog(String tag) {
        DialogFragment dialogFragment = (DialogFragment) mFragmentManager.findFragmentByTag(tag);
        if (dialogFragment != null)
            mFragmentManager
                    .beginTransaction()
                    .attach(dialogFragment)
                    .commitAllowingStateLoss();
    }
}

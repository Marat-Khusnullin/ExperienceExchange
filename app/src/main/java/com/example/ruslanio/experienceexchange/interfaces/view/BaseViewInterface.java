package com.example.ruslanio.experienceexchange.interfaces.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface BaseViewInterface {
//    DialogFragment addDialog(DialogFragment dialogFragment, String tag);
//    void dismissDialog(String tag);
//    void showDialog(String tag);
//    void hideDialog(String tag);
    void showToast(@StringRes int text);
    void showToast(String text);
    void showSnackbar(@StringRes int text);
    void showSnackbar(String text);
    Context getContext();
    void finishActivity();
    void startActivity(Intent intent);
}

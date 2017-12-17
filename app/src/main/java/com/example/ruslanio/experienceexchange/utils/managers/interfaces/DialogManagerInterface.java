package com.example.ruslanio.experienceexchange.utils.managers.interfaces;

import android.support.v4.app.DialogFragment;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public interface DialogManagerInterface {

    DialogFragment addDialog(DialogFragment dialogFragment, String tag);

    void dismissDialog(String tag);

    void hideDialog(String tag);

    void showDialog(String tag);
}

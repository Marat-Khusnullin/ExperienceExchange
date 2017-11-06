package com.example.ruslanio.experienceexchange.interfaces.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface BasePresenterInterface {
    void onCreate(Bundle saveInstanceState);
    void onInit(Bundle saveInstanceState);
    void onSaveInstanceState(Bundle saveInstanceState);
    void onRestoreInstanceState(Bundle saveInstanceState);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}

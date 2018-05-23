package com.example.ruslanio.experienceexchange.interfaces.presenter;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface LoginActivityPresenterInterface extends BasePresenterInterface{
    void onLogin(String login, String password);
    void checkForLogin();
}

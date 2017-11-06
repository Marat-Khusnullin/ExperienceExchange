package com.example.ruslanio.experienceexchange.interfaces.presenter;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface RegistrationActivityPresenterInterface extends BasePresenterInterface {
    void onSubmit(String login,String email,String password,String firstName,String lastName);
}

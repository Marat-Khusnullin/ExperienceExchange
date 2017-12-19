package com.example.ruslanio.experienceexchange.views;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.LoginActivityPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.LoginActivityViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.LoginActivityPresenter;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginActivityPresenterInterface> implements LoginActivityViewInterface {

    @BindView(R.id.et_login)
    TextInputEditText mLogin;
    @BindView(R.id.et_password)
    TextInputEditText mPassword;
    @BindView(R.id.btn_login)
    Button mLoginButton;
    @BindView(R.id.btn_registration)
    Button mRegButton;
    @BindView(R.id.btn_forgot_pass)
    Button mForgotPass;

    @BindView(R.id.ibtn_vk)
    ImageButton mVK;
    @BindView(R.id.ibtn_twitter)
    ImageButton mTwitter;
    @BindView(R.id.ibtn_g_plus)
    ImageButton mGPlus;
    @BindView(R.id.ibtn_fb)
    ImageButton mFB;

    @Override
    protected LoginActivityPresenterInterface getPresenter() {
        return new LoginActivityPresenter(this);
    }

    @Override
    protected void onInit() {
        mLoginButton.setOnClickListener(btn ->
                mPresenter.onLogin(mLogin.getText().toString(), mPassword.getText().toString()));
        mRegButton.setOnClickListener(btn -> {
            Intent intent = new Intent(getContext(), RegistrationActivity.class);
            startActivity(intent);
        });
        mForgotPass.setOnClickListener(btn -> {
            showSnackbar("I don't care!");
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }
}

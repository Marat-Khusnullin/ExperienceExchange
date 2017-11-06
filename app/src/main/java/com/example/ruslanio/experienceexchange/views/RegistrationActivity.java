package com.example.ruslanio.experienceexchange.views;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.RegistrationActivityPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.RegistrationActivityViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.RegistrationActivityPresenter;

import butterknife.BindView;

public class RegistrationActivity extends BaseActivity<RegistrationActivityPresenterInterface> implements RegistrationActivityViewInterface {

    @BindView(R.id.et_login)
    TextInputEditText mLogin;
    @BindView(R.id.et_email)
    TextInputEditText mEmail;
    @BindView(R.id.et_password)
    TextInputEditText mPassword;
    @BindView(R.id.et_first_name)
    TextInputEditText mFirstName;
    @BindView(R.id.et_last_name)
    TextInputEditText mLastName;
    @BindView(R.id.btn_submit)
    Button mSubmit;
    @BindView(R.id.cb_license_agreement)
    CheckBox mIsAgreeWithLicense;

    @Override
    protected RegistrationActivityPresenterInterface getPresenter() {
        return new RegistrationActivityPresenter(this);
    }

    @Override
    protected void onInit() {
        mSubmit.setOnClickListener(btn ->
                        mPresenter.onSubmit(mLogin.getText().toString()
                , mEmail.getText().toString()
                , mPassword.getText().toString()
                , mFirstName.getText().toString()
                , mLastName.getText().toString()));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_registration;
    }
}

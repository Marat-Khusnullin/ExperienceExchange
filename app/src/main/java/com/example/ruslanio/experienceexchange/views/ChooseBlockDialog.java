package com.example.ruslanio.experienceexchange.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ChooseBlockPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ChooseBlockViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseDialog;
import com.example.ruslanio.experienceexchange.presenters.ChooseBlockPresenter;

import butterknife.BindView;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class ChooseBlockDialog extends BaseDialog<ChooseBlockPresenterInterface> implements ChooseBlockViewInterface {

    @BindView(R.id.block_radio_group)
    RadioGroup mBlockRadioGroup;

    @BindView(R.id.btn_block_cancel)
    Button mCancel;

    @BindView(R.id.btn_block_ok)
    Button mOk;

    @Override
    protected ChooseBlockPresenterInterface getPresenter() {
        return new ChooseBlockPresenter(this);
    }

    public static ChooseBlockDialog getInstance(){
        return new ChooseBlockDialog();
    }
    @Override
    protected void onInit() {
        super.onInit();

        mBlockRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences preferences = getContext().getSharedPreferences(MAIN_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(KEY_BLOCK_ID,checkedId);
            editor.apply();
        });

        mCancel.setOnClickListener(btn -> dismissSelf(true));

        mOk.setOnClickListener(btn -> {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(MAIN_PREFERENCES, Context.MODE_PRIVATE);
            int id  = sharedPreferences.getInt(KEY_BLOCK_ID, R.id.rb_block_text);
            mPresenter.OnComplete(id);
            dismissSelf(true);
        });
    }



    @Override
    protected int getLayout() {
        return R.layout.dialog_choose_block;
    }
}

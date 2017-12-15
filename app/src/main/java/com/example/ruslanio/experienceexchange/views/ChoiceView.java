package com.example.ruslanio.experienceexchange.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Button;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.BubbleInterestAdapter;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ChoicePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ChoiceViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.ChoicePresenter;
import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class ChoiceView extends BaseActivity<ChoicePresenterInterface> implements ChoiceViewInterface {

    private static final String TAG_BUBBLE_PICKER_HOLDER = "bubble_picker_holder";

    @BindView(R.id.btn_ready)
    Button mReady;

    @Override
    protected ChoicePresenterInterface getPresenter() {
        return new ChoicePresenter(this);
    }


    @Override
    protected void onInit() {

        mReady.setOnClickListener(btn -> {
            Intent intent = new Intent(getContext(), MainView.class);
            startActivity(intent);
        });

    }

    @Override
    public void nextView(List<Integer> ids) {
        Intent intent = new Intent(getContext(), MainView.class);
        intent.putIntegerArrayListExtra(KEY_INTERESTS_ID_LIST, (ArrayList<Integer>) ids);
        startActivity(intent);
    }


    @Override
    public void showBubblePicker(List<Interest> interests){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment bubblePickerHolder = fragmentManager.findFragmentByTag(TAG_BUBBLE_PICKER_HOLDER);

        if (bubblePickerHolder == null)
            bubblePickerHolder = BubblePickerHolderView.getInstance(interests);
        fragmentManager
                .beginTransaction()
                .replace(R.id.container_bubble_picker,bubblePickerHolder,TAG_BUBBLE_PICKER_HOLDER)
                .commit();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_choice_v2;
    }
}

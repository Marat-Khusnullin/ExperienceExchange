package com.example.ruslanio.experienceexchange.presenters;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ChooseBlockPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ChooseBlockViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.utils.rxbus.BusEvents;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class ChooseBlockPresenter extends BasePresenter<ChooseBlockViewInterface> implements ChooseBlockPresenterInterface {

    public ChooseBlockPresenter(ChooseBlockViewInterface view) {
        super(view);
    }

    @Override
    public void OnComplete(int id) {
        switch (id) {
            case R.id.rb_block_text:
                publish(BusEvents.TAG_BLOCK_CHOSEN, BLOCK_TEXT);
                break;
            case R.id.rb_block_image:
                publish(BusEvents.TAG_BLOCK_CHOSEN, BLOCK_IMAGE);
                break;
            case R.id.rb_block_video:
                publish(BusEvents.TAG_BLOCK_CHOSEN, BLOCK_VIDEO);
                break;
        }
    }
}

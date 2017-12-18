package com.example.ruslanio.experienceexchange.presenters;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.LessonBlock;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempBlock;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingLessonPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingLessonViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.utils.Util;
import com.example.ruslanio.experienceexchange.utils.rxbus.BusEvents;
import com.example.ruslanio.experienceexchange.utils.rxbus.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingLessonPresenter extends BasePresenter<CourseCreatingLessonViewInterface>
        implements CourseCreatingLessonPresenterInterface {
    private ApiManager mApiManager;
    private DataBaseManager mDataBaseManager;
    private List<TempBlock> mBlocks;
    private int mCount = -1;
    private static final String KEY_COUNT = "key_block_count";

    public CourseCreatingLessonPresenter(CourseCreatingLessonViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mApiManager = ApiManager.getInstance();
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
        mBlocks = new ArrayList<>();

        if (saveInstanceState != null)
            mCount = saveInstanceState.getInt(KEY_COUNT, -2);
        if (mCount < 0) {
            mCount = 0;
        }
    }

    @Subscriber(tag = BusEvents.TAG_BLOCK_CHOSEN)
    public void inflateBlock(Integer blockType) {
        switch (blockType) {
            case BLOCK_TEXT:
                mView.inflateText();
                break;
            case BLOCK_IMAGE:
                mView.inflateImage();
                break;
            case BLOCK_VIDEO:
                mView.inflateVideo();
                break;
        }
    }

    @Override
    public void onTextBlock(String text) {
        saveBlock(BLOCK_TEXT, text);
    }

    @Override
    public void onImageBlock(String uri) {
        mApiManager.uploadImage(new File(getRealPathFromURI(Uri.parse(uri))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imageResponce -> {
                    if (Util.checkCode(imageResponce.getStatus())) {
                        String imageCode = imageResponce.getResult();

                        saveBlock(BLOCK_IMAGE, imageCode);

                        mView.showSnackbar(imageCode);
                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                }, throwable -> {
                    mView.showSnackbar(R.string.server_error);
                    throwable.printStackTrace();
                });
    }

    @Override
    public void buildLesson(String lessonName) {
        TempLesson lesson = new TempLesson();
        lesson.setName(lessonName);
        lesson.setBlocks(mBlocks);
        mDataBaseManager.insertTemporaryLesson(lesson);
        publish(BusEvents.TAG_LESSON_CREATED);
    }


    private void saveBlock(int type, String value) {
        TempBlock block = new TempBlock();
        block.setOrder(mCount);
        block.setType(type);
        block.setValue(value);
        mBlocks.add(block);
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = mView.getContext().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        if (mCount >= 0)
            saveInstanceState.putInt(KEY_COUNT, mCount);
    }
}

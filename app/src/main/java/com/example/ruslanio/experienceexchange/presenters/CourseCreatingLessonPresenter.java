package com.example.ruslanio.experienceexchange.presenters;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempBlock;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingLessonPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingLessonViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.pojo.image.ImageResponce;
import com.example.ruslanio.experienceexchange.utils.BlockInfoHolder;
import com.example.ruslanio.experienceexchange.utils.Util;
import com.example.ruslanio.experienceexchange.utils.rxbus.BusEvents;
import com.example.ruslanio.experienceexchange.utils.rxbus.Subscriber;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingLessonPresenter extends BasePresenter<CourseCreatingLessonViewInterface>
        implements CourseCreatingLessonPresenterInterface {
    private ApiManager mApiManager;
    private DataBaseManager mDataBaseManager;
    private static final String KEY_COUNT = "key_block_count";
    private String mLessonName;
    private List<BlockInfoHolder> mHolders;
    private List<Integer> mCounts;
    private int mLessonCount;

    public CourseCreatingLessonPresenter(CourseCreatingLessonViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mApiManager = ApiManager.getInstance();
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
        mCounts = new ArrayList<>();


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
//        saveBlock(BLOCK_TEXT, text);
    }

    @Override
    public void onImageBlock(String uri) {
//        mApiManager.uploadImage(new File(getRealPathFromURI(Uri.parse(uri))))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(imageResponce -> {
//                    if (Util.checkCode(imageResponce.getStatus())) {
//                        String imageCode = imageResponce.getResult();
//
//                        saveBlock(BLOCK_IMAGE, imageCode);
//
//                        mView.showSnackbar(imageCode);
//                    } else {
//                        mView.showSnackbar(R.string.connection_error);
//                    }
//                }, throwable -> {
//                    mView.showSnackbar(R.string.server_error);
//                    throwable.printStackTrace();
//                });
    }

    @Override
    public void buildLesson(String lessonName, List<BlockInfoHolder> infoHolders, int count) {
        mLessonName = lessonName;
        mHolders = infoHolders;
        mLessonCount = count;
        sendImages(infoHolders);
    }

    private void sendImages(List<BlockInfoHolder> holders) {
        List<String> images = new ArrayList<>();
        int count = 0;
        for (BlockInfoHolder holder : holders) {
            if (holder.getType() == BLOCK_IMAGE) {
                images.add(Util.getRealPathFromURI(Uri.parse(holder.getValue()), mView.getContext()));
                mCounts.add(count);
            }
            count++;
        }
        TempAsyncTask asyncTask = new TempAsyncTask();
        asyncTask.execute(images.toArray(new String[images.size()]));
    }


    private void onImagesSend(List<String> images) {
        List<TempBlock> tempBlocks = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            tempBlocks.add(saveBlock(BLOCK_IMAGE, images.get(i), mCounts.get(i)));
        }
        for (BlockInfoHolder holder : mHolders) {
            if (holder.getType() == BLOCK_TEXT)
                tempBlocks.add(saveBlock(BLOCK_TEXT, holder.getValue(), holder.getCount()));
        }
        saveLesson(tempBlocks);
    }

    private void saveLesson(List<TempBlock> tempBlocks) {
        TempLesson lesson = new TempLesson();
        lesson.setName(mLessonName);
        lesson.setBlocks(tempBlocks);
        lesson.setCount(mLessonCount);
        mDataBaseManager.insertTemporaryLesson(lesson);
        publish(BusEvents.TAG_LESSON_CREATED);
    }

    private TempBlock saveBlock(int type, String value, int count) {
        TempBlock block = new TempBlock();
        block.setOrder(count);
        block.setType(type);
        block.setValue(value);
        return block;
    }




    private class TempAsyncTask extends AsyncTask<String, Integer, String> {

        private StringBuilder mStringBuilder;
        private List<String> mImages;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mStringBuilder = new StringBuilder();
            mImages = new ArrayList<>();
        }

        @Override
        protected String doInBackground(String... strings) {

            for (String imagePath : strings) {
                Call<ImageResponce> call = mApiManager.uploadImageLikeDCP(new File(imagePath));
                try {
                    Response<ImageResponce> response = call.execute();
                    if (response.isSuccessful() && Util.checkCode(response.code())) {
                        ImageResponce imageResponce = response.body();
                        if (imageResponce != null) {
                            String value = imageResponce.getResult();
                            mImages.add(value);
                            mStringBuilder.append(value).append(" ");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return mStringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onImagesSend(mImages);

        }
    }


    //        Observable<TempBlock> observable = Observable.create(e -> {
//            for (BlockInfoHolder holder : holders) {
//                switch (holder.getType()) {
//                    case BLOCK_TEXT:
//                        e.onNext(saveBlock(BLOCK_TEXT,
//                                holder.getValue(),
//                                holder.getCount()));
//                        break;
//                    case BLOCK_IMAGE:
//                        mApiManager.uploadImage(new File(getRealPathFromURI(Uri.parse(holder.getValue()))))
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(imageResponce -> {
//                                    if (Util.checkCode(imageResponce.getStatus())) {
//                                        String imageCode = imageResponce.getResult();
//
//                                        e.onNext(saveBlock(BLOCK_IMAGE,
//                                                imageCode,
//                                                holder.getCount()));
//                                    } else {
//                                        mView.showSnackbar(R.string.connection_error);
//                                    }
//                                }, throwable -> {
//                                    mView.showSnackbar(R.string.server_error);
//                                    throwable.printStackTrace();
//                                });
//                        break;
//                    case BLOCK_VIDEO:
//                        e.onNext(saveBlock(BLOCK_VIDEO,
//                                holder.getValue(),
//                                holder.getCount()));
//                        break;
//                }
//            }
//        });
//
//        List<TempBlock> result = new ArrayList<>();
//        observable
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(result::add);
//        return result;

}

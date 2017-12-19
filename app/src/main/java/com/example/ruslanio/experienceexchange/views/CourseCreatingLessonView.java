package com.example.ruslanio.experienceexchange.views;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingLessonPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingLessonViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.CourseCreatingLessonPresenter;
import com.example.ruslanio.experienceexchange.utils.BlockInfoHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingLessonView extends BaseFragment<CourseCreatingLessonPresenterInterface>
        implements CourseCreatingLessonViewInterface {

    private static final String TAG_CHOOSE_BLOCK = "tag_choose_block";
    private static final String KEY_LESSON_COUNT = "key_lesson_count";

    @BindView(R.id.btn_lesson_done)
    Button mDone;
    @BindView(R.id.btn_lesson_add_block)
    Button mAddBlock;
    @BindView(R.id.lesson_count)
    TextView mLessonCount;
    @BindView(R.id.lesson_close)
    ImageButton mClose;
    @BindView(R.id.et_lesson_name)
    TextInputEditText mLessonName;
    @BindView(R.id.lesson_blocks_holder)
    LinearLayout mBlocksHolder;


    private ImageView mCurrentImage;

    @Override
    protected CourseCreatingLessonPresenterInterface getPresenter() {
        return new CourseCreatingLessonPresenter(this);
    }

    public static CourseCreatingLessonView getInstance(int lessonCount){
        CourseCreatingLessonView view = new CourseCreatingLessonView();
        Bundle args = new Bundle();
        args.putInt(KEY_LESSON_COUNT, lessonCount);
        view.setArguments(args);
        return view;
    }
    @Override
    protected void onInit() {
        super.onInit();

        int count = getArguments().getInt(KEY_LESSON_COUNT);
        mLessonCount.setText(mLessonCount.getText().toString() + count);

        mAddBlock.setOnClickListener(btn -> {
            addDialog(ChooseBlockDialog.getInstance(), TAG_CHOOSE_BLOCK);
        });

        mDone.setOnClickListener(btn -> {
            View currentView;
            BlockInfoHolder holder;
            List<BlockInfoHolder> holders = new ArrayList<>();
            for (int i = 0; i < mBlocksHolder.getChildCount(); i++){
                holder = new BlockInfoHolder();
                currentView = mBlocksHolder.getChildAt(i);
                switch (currentView.getId()){
                    case R.id.block_text:
                        TextInputEditText editText = currentView.findViewById(R.id.et_block_text);
                        String text = editText.getText().toString();
                        holder.setType(BLOCK_TEXT);
                        holder.setCount(i);
                        holder.setValue(text);
                        break;
                    case R.id.block_image:
                        ImageView image = currentView.findViewById(R.id.iv_block_image);
                        String uri = image.getContentDescription().toString();
                        holder.setType(BLOCK_IMAGE);
                        holder.setCount(i);
                        holder.setValue(uri);
                        break;
                    case R.id.rb_block_video:
                        break;

                }
                holders.add(holder);
            }
            mPresenter.buildLesson(mLessonName.getText().toString(), holders, count);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_course_creating_lesson;
    }

    @Override
    public void inflateText() {
        View textBlock = getLayoutInflater().inflate(R.layout.item_block_text,null);
        mBlocksHolder.addView(textBlock);
    }

    @Override
    public void inflateImage() {
        View imageBlock = getLayoutInflater().inflate(R.layout.item_block_image,null);
        Button chooseImage = imageBlock.findViewById(R.id.btn_block_image_choose);
        ImageView image = imageBlock.findViewById(R.id.iv_block_image);

        chooseImage.setOnClickListener(btn -> {
            mCurrentImage = image;

            Intent pickImageIntent = new Intent();
            pickImageIntent.setType("image/*");
            pickImageIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(pickImageIntent,PICK_IMAGE_REQUEST_CODE);
        });
        mBlocksHolder.addView(imageBlock);
    }

    @Override
    public void inflateVideo() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == PICK_IMAGE_REQUEST_CODE && data != null){
                Uri uri = data.getData();
                Picasso.with(getContext())
                        .load(uri)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .noFade()
                        .into(mCurrentImage);
                mCurrentImage.setContentDescription(uri.toString());

            }else {
                showSnackbar(R.string.image_was_not_chosen);
            }
        } else {
            showSnackbar(R.string.please_try_again);
        }
    }


}

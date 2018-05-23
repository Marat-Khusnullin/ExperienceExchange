package com.example.ruslanio.experienceexchange.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.LessonsAdapter;
import com.example.ruslanio.experienceexchange.adapters.TempLessonAdapter;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.LessonTrue;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseViewActivityPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseViewActivityInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.CourseViewActivityPresenter;
import com.example.ruslanio.experienceexchange.utils.views.EntitledTextView;
import com.like.LikeButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseViewActivity extends BaseActivity<CourseViewActivityPresenterInterface> implements CourseViewActivityInterface {

    @BindView(R.id.course_main_name)
    TextView mCourseName;
    @BindView(R.id.course_main_author)
    TextView mAuthor;
    @BindView(R.id.course_short_main_description)
    TextView mDescription;
    @BindView(R.id.course_to_process_lamp)
    ImageButton mToProcess;
    @BindView(R.id.author_main_avatar)
    ImageView mAuthorAvatar;
    @BindView(R.id.rv_main_course_lessons)
    RecyclerView recyclerView;

    LessonsAdapter adapter;


    @Override
    protected CourseViewActivityPresenterInterface getPresenter() {
        return new CourseViewActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        ButterKnife.bind(this);
        adapter = new LessonsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




    }

    @Override
    protected void onInit() {



    }

    @Override
    protected int getLayout() {
        return R.layout.activity_course_view;
    }


    public int getCourseId() {
        return getIntent().getIntExtra("courseId", 666);
    }

    public void setData(Course course){
          mCourseName.setText(course.getCourseName());
          mAuthor.setText(course.getAuthor());
          mDescription.setText(course.getDescription());
    }

    public void setLessons(List<LessonTrue> lessons) {
        Toast.makeText(this, lessons.size()+ "", Toast.LENGTH_SHORT).show();
        recyclerView.setAdapter(adapter);
        adapter.setLessons(lessons);

    }
}

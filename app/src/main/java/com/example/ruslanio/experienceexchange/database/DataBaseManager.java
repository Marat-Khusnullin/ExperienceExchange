package com.example.ruslanio.experienceexchange.database;

import android.content.Context;

import com.example.ruslanio.experienceexchange.database.abstracts.ExExDatabase;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.LessonBlock;
import com.example.ruslanio.experienceexchange.database.model.User;

import java.util.List;

/**
 * Created by Ruslanio on 13.12.2017.
 */

public class DataBaseManager {

    private static DataBaseManager MANAGER;
    private ExExDatabase mDatabase;

    private DataBaseManager(Context context) {
        mDatabase = ExExDatabase.getInstance(context);
    }

    public static DataBaseManager getInstance(Context context){
        if (MANAGER == null){
            MANAGER = new DataBaseManager(context);
        }
        return MANAGER;
    }

    public List<Interest> getAllInterests(){
        return mDatabase.getInterestsDao().getAll();
    }

    public void insertInterest(Interest interest){
        mDatabase.getInterestsDao().add(interest);
    }

    public void insertInterest(List<Interest> interests){
        mDatabase.getInterestsDao().add(interests.toArray(new Interest[interests.size()]));
    }

    public void setNewInterests(List<Interest> interests){
        mDatabase.getInterestsDao().clearInterests();
        insertInterest(interests);
    }

    public User getCurrentUser(){
        return mDatabase.getUsersDao().getUser().get(0);
    }

    public void updateCurrentUser(User user){
        mDatabase.getUsersDao().clearTable();
        mDatabase.getUsersDao().add(user);
    }

    public String getCurrentToken(){
        return getCurrentUser().getToken();
    }

    public Interest getInterestByName(String name){
        return mDatabase.getInterestsDao().getByName(name);
    }

    public void updateInterest(Interest interest){
        mDatabase.getInterestsDao().update(interest);
    }


    public void insertLesson(Lesson lesson){
        lesson.setTemporary(true);
        List<LessonBlock> blocks = lesson.getBlocks();
        long id = mDatabase.getLessonDao().add(lesson);
        for (LessonBlock block: blocks){
            block.setLessonId(id);
        }
        mDatabase.getLessonBlockDao().add(blocks.toArray(new LessonBlock[blocks.size()]));
    }

    public List<Lesson> getTemporaryLessons(){
        List<Lesson> lessons = mDatabase.getLessonDao().getAllTemporary();
        for (Lesson lesson: lessons){
            lesson.setBlocks(mDatabase.getLessonBlockDao().getAllByLessonId(lesson.getId()));
        }
        return lessons;
    }


    public void insertCourse(Course course){
        String author = getCurrentUser().getFullName();
        course.setAvailableLessons(course.getLessons().size());
        course.setAuthor(author);
        mDatabase.getCourseDao().add(course);
    }





}
